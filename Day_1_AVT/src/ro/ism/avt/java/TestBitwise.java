package ro.ism.avt.java;
import java.util.*;

public class TestBitwise {
	
	public static String getHexString(byte[] value) {
		
		StringBuilder sb = new StringBuilder();
		
		for(byte byteValue : value) {
			sb.append(String.format("%02X ", byteValue));
		}
		return sb.toString();
		
	}
	
	public static void main(String[] args) {
		
		//define a 1 byte key with bit 5=1
		byte key1 = 32;
		byte key2 = (byte) 0b00100000;
		byte key3 = 1;
		
		System.out.println("Key1: " + key1);
		System.out.println("Key2: " + key2);
		System.out.println("Key3: " + key3);
		
		byte key4 = 0b01001000;
		byte key5 = 1<<6|1<<3;
		
		System.out.println("Key4: " + key4);
		System.out.println("Key5: " + key5);
		
		//defining values in hexa
		byte nounce = (byte) 0xA3;
		
		//define a DES key on 8 bytes where byte 3 has all bits 1 and the rest are 0
		byte[] desKey = {0,0,0,0, (byte)0b11111111, 0,0,0};
		byte[] desKey2 = {0,0,0,0, (byte)0xFF, 0,0,0};
		//define a AES key on a 128 bits where byte 5 = AA and byte 7 is 3F
		
		
		byte[] aesKey = new byte[16];
		//aesKey[5] //BIG MISTAKE
		aesKey[10] = (byte) 0xAA;  //15-5
		aesKey[8] = (byte) 0X3F;	//15-7
		
		//printing values
		
		System.out.printf("The 1 byte key is %x \n", key4);
		System.out.printf("The 1 byte nounce is %x \n", nounce);
		System.out.printf("The 1 byte nounce is %X \n", nounce);

		System.out.println("The AES key is "+ getHexString(aesKey));
		
		//string conversions from byte representation
		//DON'T DO THIS
		
		byte[] desKey3 = {0x30, (byte) 0x89, 0x00, 0x01, 0x02, (byte) 0xD3, 0x13, (byte) 0xFF};
		
		System.out.println("The DES key is "+ getHexString(desKey3));
		
		//DON'T DO THIS YOU JUST LOSE THE BINARY VALUES
//		String desPass = new String(desKey3);
//		
//		System.out.println("Same DES key: " + desPass);
		
		//Storing binary values as base64, alternative is hex string
		//This is encoding!
		
		String Base64Representation = Base64.getEncoder().encodeToString(desKey3);
		System.out.println("DES key as Base64 string " + Base64Representation);
		
		byte[] desKey3Copy = 
				Base64.getDecoder().decode(Base64Representation);
		System.out.println("DES key copy " + desKey3Copy); //this prints the memory address in the java VM
		System.out.println("DES key copy " + getHexString(desKey3Copy)); //this prints the key
		
		//comparing byte arrays
		
		byte[] desKey4 = {0x30, (byte) 0x89, 0x00, 0x01, 0x02, (byte) 0xD3, 0x13, (byte) 0xFF};

		
		//COMPARISON
		
		//WRONG - DON'T DO THIS
		if(desKey3 == desKey4) {
			System.out.println("Are equal");
		}else {
			System.out.println("Different"); //we are comparing here the memory addresses so don't compare with ==
		}
		
		//RIGHT
		if(Arrays.equals(desKey3, desKey4)){
			System.out.println("Are equal");
		}else {
			System.out.println("Different"); //does a deep check, with values not references
		}
		
		
		//string handling in java
		
		String userName = "John";
		String userName2 = "John"; //This goes in string constant pool instead of heap with same mem address and different index
		
		if(userName == userName2) {
			System.out.println("Same user");
		}else {
			System.out.println("Different user");
		}
		
		//---------
		
		userName = new String("John");
		userName2 = new String("John"); //This creates normal string objects in heap 

		if(userName == userName2) {
			System.out.println("Same user");
		}else {
			System.out.println("Different user");
		}
		
		//-------------------------PART 2 -------------------------------
		System.out.println("-----------------------------------");

		
		//BIT LEVEL SHIFTING
		
		byte anotherKey= 0b00110111;
		System.out.println(Integer.toBinaryString(anotherKey));
		
		anotherKey = (byte) (anotherKey << 1);
		System.out.println(Integer.toBinaryString(anotherKey));
		
		
		
	}
	
}
