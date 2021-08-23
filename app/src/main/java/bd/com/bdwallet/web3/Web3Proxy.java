package bd.com.bdwallet.web3;

import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.Web3jFactory;
import org.web3j.protocol.core.methods.response.EthGasPrice;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.ChainId;
import org.web3j.tx.RawTransactionManager;
import org.web3j.tx.response.NoOpProcessor;
import org.web3j.tx.response.TransactionReceiptProcessor;
import org.web3j.utils.Convert;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;

import bd.com.appcore.util.AppSettings;
import bd.com.bdwallet.module.wallet.ApiConfig;
import bd.com.bdwallet.util.HttpUtils;
import bd.com.bdwallet.web3.contract.NulsStandardToken;
import bd.com.bdwallet.web3.contract.SuperConductToken;
import bd.com.walletdb.entity.TokenEntity;
import bd.com.walletdb.entity.WalletEntity;
import bd.com.walletdb.manager.WalletDBManager;


public class Web3Proxy {

    //private String contractaddress = "0x0f46a24b42923aae949cd82b78b90b21e990cbad";
    private String contractaddress = "0x0f46a24b42923aae949cd82b78b90b21e990cbad";
    public static final String SCT_CONTRACT_ADDRESS = "0xsctcontractaddress";
    //    public static final BigInteger GAS_PRICE = BigInteger.valueOf(10000000000L);
//    public static BigInteger GAS_LIMIT = BigInteger.valueOf(2000000);
    public static BigInteger GAS_PRICE = BigInteger.valueOf(22_000_000_000L);
    //public static  BigInteger GAS_LIMIT = BigInteger.valueOf(2200000);
    public static BigInteger GAS_LIMIT = BigInteger.valueOf(60000);
    public static BigInteger DEPLOY_GAS_LIMIT = BigInteger.valueOf(2000000);
    public static BigInteger PACK_SOSO_GAS_LIMIT = BigInteger.valueOf(1_000_010_000L);
    private static Web3Proxy web3Proxy = new Web3Proxy();

    private Web3Proxy() {
    }   

    public Web3j getWeb3j() {
        HttpService httpService = new HttpService(ApiConfig.getWeb3jUrlProxy(), HttpUtils.createOkHttpClient(), false);
        Web3j web3j = Web3jFactory.build(httpService);
        return web3j;
    }

    public static Web3Proxy getWeb3Proxy() {
        return web3Proxy;
    }

    public SuperConductToken deploy(Credentials credentials) throws Exception {
        SuperConductToken token = SuperConductToken.deploy(getWeb3j(), credentials, GAS_PRICE, GAS_LIMIT).send();
        String conaddress = token.getContractAddress();
        // setContractaddress(conaddress);
        return token;
    }

    public String deploy(Credentials credentials, String initAumount, String name, String symbol) throws Exception {
        NulsStandardToken token = NulsStandardToken.deploy(getWeb3j(), credentials, GAS_PRICE, DEPLOY_GAS_LIMIT, new BigInteger(initAumount), name, new BigInteger("18"), symbol).send();
        String conaddress = token.getContractAddress();
        //setContractaddress(conaddress);
        return conaddress;
    }
}