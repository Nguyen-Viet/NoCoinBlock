package problemdomain;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import block.Block;


//https://blockchain.info/block-index/506679

public class SecretChain
{

	public static ArrayList<Block> blockchain = new ArrayList<Block>();
	public static int difficulty = 1;
	
	
	public static void main(String[] args) throws NoSuchAlgorithmException
	{
		
		// GENESIS BLOCK
		Block genesisBlock = new Block("Hi im the first block", "0");
		System.out.println("Hash for block 1 : " + genesisBlock.hash);
//		blockchain.add(genesisBlock);
//		System.out.println("Trying to Mine block 1... ");
//		blockchain.get(0).mine(difficulty);
		
		
		// SECOND BLOCK
		Block secondBlock = new Block("Yo im the second block",genesisBlock.hash);
		System.out.println("Hash for block 2 : " + secondBlock.hash);
//		blockchain.add(secondBlock);
//		System.out.println("Trying to Mine block 2... ");
//		blockchain.get(1).mine(difficulty);
		
		
		// THIRD BLOCK
		Block thirdBlock = new Block("Hey im the third block",secondBlock.hash);
		System.out.println("Hash for block 3 : " + thirdBlock.hash);
//		blockchain.add(thirdBlock);
//		System.out.println("Trying to Mine block 3... ");
//		blockchain.get(2).mine(difficulty);
		
		
		
		System.out.println("\nSecretChain is valid: " + isChainValid());
		System.out.println("\nBLOCKCHAIN");
		
		for(Block blocks: blockchain)
		{
			System.out.println("\n\t" + blocks.toString());
		}
		
		
		
//		secondBlock.setData("no secret");
//		System.out.println("\nSecretChain is valid: " + isChainValid());
		

	}
	
	
	// Check hash variable to see if it equals the calculated hash value
	public static Boolean isChainValid() throws NoSuchAlgorithmException
	{
		Block currBlock;
		Block prevBlock;
		
		
		for( int i=1; i < blockchain.size(); i++)
		{
			currBlock = blockchain.get(i);
			prevBlock = blockchain.get(i-1);
			
			// registered hash and calculated hash
			if( !currBlock.hash.equals( currBlock.calcHash() ))
			{
				System.out.println("Current Hashes not equal");	
				return false;
			}
			
			// previous hash and registered previous hash
			if( !prevBlock.hash.equals( currBlock.previousHash ))
			{
				System.out.println("Previous Hashes not equal");	
				return false;
			}
			
		}
		
		return true;
	}

}
