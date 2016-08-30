package br.com.assinador.bc;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.Security;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.cert.jcajce.JcaCertStore;
import org.bouncycastle.cert.jcajce.JcaX509CertificateConverter;
import org.bouncycastle.cms.CMSException;
import org.bouncycastle.cms.CMSProcessable;
import org.bouncycastle.cms.CMSProcessableByteArray;
import org.bouncycastle.cms.CMSSignedData;
import org.bouncycastle.cms.CMSSignedDataGenerator;
import org.bouncycastle.cms.CMSTypedData;
import org.bouncycastle.cms.SignerId;
import org.bouncycastle.cms.SignerInformation;
import org.bouncycastle.cms.SignerInformationStore;
import org.bouncycastle.cms.SignerInformationVerifier;
import org.bouncycastle.cms.SignerInformationVerifierProvider;
import org.bouncycastle.cms.jcajce.JcaSignerInfoGeneratorBuilder;
import org.bouncycastle.cms.jcajce.JcaSimpleSignerInfoVerifierBuilder;
import org.bouncycastle.operator.ContentSigner;
import org.bouncycastle.operator.OperatorCreationException;
import org.bouncycastle.operator.jcajce.JcaContentSignerBuilder;
import org.bouncycastle.operator.jcajce.JcaDigestCalculatorProviderBuilder;
import org.bouncycastle.util.Store;

import br.com.assinador.bc.exception.AssinaturaInvalidaException;
import br.com.assinador.leitor.LeitorKeyStore;
import br.com.assinador.leitor.LeitorKeyStoreFactory;

public class AssinaturaDigitalService {

	//private static final String PROVIDER_NAME = "SunMSCAPI";
	private static final String SHA1WITH_RSA = "SHA1withRSA";
	private String senha = "123456";
	private PrivateKey privateKey;
	private LeitorKeyStore leitorKeyStore;

	public AssinaturaDigitalService() {
		this.leitorKeyStore = LeitorKeyStoreFactory.getLeitorKeyStore();
		Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
	}
	
	public AssinaturaDigitalService(String senha) {
		this();
		this.senha = senha;
	}

	@SuppressWarnings({ "rawtypes" })
	public byte[] assinar(byte[] arquivo)
			throws GeneralSecurityException, CMSException, IOException, OperatorCreationException {

		X509CertificateHolder certificado = obterCertificado();
		List certList = Arrays.asList(certificado);
		Store certStore = new JcaCertStore(certList);
		
		PrivateKey privateKey = getPrivateKey();
		
		ContentSigner sha1Signer = new JcaContentSignerBuilder(SHA1WITH_RSA).build(privateKey);
		
		CMSSignedDataGenerator signedDataGenerator = new CMSSignedDataGenerator();
		
		signedDataGenerator.addSignerInfoGenerator(new JcaSignerInfoGeneratorBuilder(
				new JcaDigestCalculatorProviderBuilder().build()).build(sha1Signer,
						certificado));
		
		signedDataGenerator.addCertificates(certStore);
		CMSTypedData msg = new CMSProcessableByteArray(arquivo);
		CMSSignedData sigData = signedDataGenerator.generate(msg, true);

		return sigData.getEncoded();
	}

	@SuppressWarnings("unchecked")
	public static String obterSubjectDn(byte[] arquivo) throws CMSException, CertificateException {
		String dn = null;
		
		CMSSignedData signature = new CMSSignedData(arquivo);
		Store<?> cs = signature.getCertificates();
		SignerInformationStore signers = signature.getSignerInfos();
		Collection<?> c = signers.getSigners();
		Iterator<?> it = c.iterator();
		while (it.hasNext()) {
			SignerInformation signer = (SignerInformation) it.next();
			Collection<?> certCollection = cs.getMatches(signer.getSID());
			Iterator<?> certIt = certCollection.iterator();
			X509CertificateHolder cert = (X509CertificateHolder) certIt
					.next();
			X509Certificate certificate = new JcaX509CertificateConverter()
					.getCertificate(cert);
			dn = certificate.getSubjectDN().getName();

		}
		return dn;
	}
	
	public static byte[] extrairConteudoDeP7s(byte[] arquivo) throws CMSException {
		CMSSignedData signature = new CMSSignedData(arquivo);
		CMSProcessable sc = signature.getSignedContent();
		return (byte[]) sc.getContent();
	}

	public boolean verificarAssinatura(byte[] arquivo) throws CMSException, GeneralSecurityException, IOException {
		CMSSignedData arquivoAssinado = new CMSSignedData(arquivo);
		final X509CertificateHolder certificadoOriginal = obterCertificado();
		SignerInformationVerifierProvider informationVerifierProvider = new SignerInformationVerifierProvider() {
	        public SignerInformationVerifier get(SignerId signerId) throws OperatorCreationException {
	            try {
					return new JcaSimpleSignerInfoVerifierBuilder().build(certificadoOriginal);
				} catch (CertificateException e) {
					throw new AssinaturaInvalidaException("Falha na verificação do arquivo assinado.");
				}
	        }
	    };
	    if (!arquivoAssinado.verifySignatures(informationVerifierProvider)) {
	    	throw new AssinaturaInvalidaException("Falha na verificação do arquivo assinado.");
	    } else {
	    	return true;
		}
	}
	
	private X509CertificateHolder obterCertificado() throws GeneralSecurityException, IOException {
		KeyStore keystore = leitorKeyStore.getKeystore(this.senha.toCharArray());
		String alias = keystore.aliases().nextElement();
		java.security.cert.Certificate c = keystore.getCertificate(alias);
		return new X509CertificateHolder(c.getEncoded());
	}

	private PrivateKey getPrivateKey() throws GeneralSecurityException, IOException {
		if (this.privateKey == null) {
			this.privateKey = carregarPrivateKey();
		}
		return this.privateKey;
	}

	private PrivateKey carregarPrivateKey() throws GeneralSecurityException, IOException {
		KeyStore keystore = leitorKeyStore.getKeystore(this.senha.toCharArray());
		String alias = keystore.aliases().nextElement();
		return (PrivateKey) keystore.getKey(alias, this.senha.toCharArray());
	}

}
