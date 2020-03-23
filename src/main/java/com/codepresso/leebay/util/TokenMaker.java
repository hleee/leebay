package com.codepresso.leebay.util;

import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TokenMaker {

	static Logger logger = LoggerFactory.getLogger(TokenMaker.class);

	public static String makeToken() {

		int length = 64;
		StringBuffer buffer = new StringBuffer();
		Random random = new Random();

		String[] charArray = "a,b,c,d,e,f,g,h,i,j,k,l,m,n,o,p,q,r,s,t,u,v,w,x,y,z,0,1,2,3,4,5,6,7,8,9".split(",");
		for (int i = 0; i < length; i++) {
			buffer.append(charArray[random.nextInt(charArray.length)]);
		}

		logger.info(buffer.toString());
		return buffer.toString();
	}
}
