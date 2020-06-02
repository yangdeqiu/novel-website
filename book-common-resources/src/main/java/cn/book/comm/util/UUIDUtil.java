package cn.book.comm.util;

import java.util.UUID;

public class UUIDUtil {
	public static String getUUID(){
		UUID uuid=UUID.randomUUID();
		return uuid.toString();
	}
	public static void main(String[] args) {
		System.out.println(getUUID());
	}
}
