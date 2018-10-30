package block;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

import security.Security;


public class Block
{
	
	public 	String 		hash;			// the block signature 
	public 	String 		previousHash;   // the previous block signature
	public  String 		data;			// can be a merkle tree
	private long 		timeStamp;		
	private int 		nonce;			
	

	public Block(String data, String previousHash) throws NoSuchAlgorithmException 
	{
		this.data 			= data;
		this.previousHash 	= previousHash;
		this.timeStamp 		= new Date().getTime();
		this.hash 			= calcHash();
	}
	
	
	public void setData(String data)
	{
		this.data = data;
	}
	

	//Calculate new hash value based on block contents 
	public String calcHash() throws NoSuchAlgorithmException
	{
		String calculated = Security.hashIt( previousHash + 
											 Long.toString(timeStamp) +
											 Integer.toString(nonce) + 
											 data );
		
		System.out.println("new value : " + calculated);
		return calculated;
	}
	
	
	/**
	 * Mining objective is to hash the signature of the block
	 * with another number to try and get a new String value
	 * that begins with a certain number of zeros
	 * 
	 * @param difficulty - difficulty is a number that regulates how long it takes for miners to
	 *                     add new blocks of transactions to the blockchain.
	 *  				   The greater the difficulty, the lower the target, 
	 *  				   and the more difficult it is to find a block hash that is below or equal this value.
	 *  
	 * @throws NoSuchAlgorithmException
	 */
	public void mine( int difficulty ) throws NoSuchAlgorithmException
	{
		
		String target = new String( new char[ difficulty ] ).replace("\0", "0");

		
		
		// Try and get a new string that begins with a certain number of zeros
		// If hash header == the target then we have a winner    =) <-----happy face
		// If not, increment the nonce and try again             ='( <-----sad face
		while( !hash.substring( 0,difficulty ).equals( target ))
		{
			nonce++;
			hash = calcHash();
		}
		
		
		System.out.println("target is " + target);
		System.out.println("Block Mined!!! : " + hash);
	}
	
	
	
	@Override
	public String toString()
	{
		return "Block [ hash = " + hash + 
					  ",\n\t\tpreviousHash = " + previousHash + 
					  ",\n\t\ttimeStamp = " + timeStamp + 
					  ",\n\t\tdata = " + data
				+ " ]";
	}
	
	
}
