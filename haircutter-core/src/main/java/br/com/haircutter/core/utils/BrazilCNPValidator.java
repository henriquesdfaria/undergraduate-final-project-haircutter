package br.com.haircutter.core.utils;

public class BrazilCNPValidator {
	private static final int[] CPFWeight = { 11, 10, 9, 8, 7, 6, 5, 4, 3, 2 };
	private static final int[] CNPJWeight = { 6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2 };


	public static boolean isValidCPF(String cpf) {
		if ((cpf == null) || (cpf.length() != 11))
			return false;

		Integer digitoOne = calculateDigit(cpf.substring(0, 9), CPFWeight);
		Integer digitoTwo = calculateDigit(cpf.substring(0, 9) + digitoOne, CPFWeight);
		return cpf.equals(cpf.substring(0, 9) + digitoOne.toString() + digitoTwo.toString());
	}

	public static boolean isValidCNPJ(String cnpj) {
		if ((cnpj == null) || (cnpj.length() != 14) || (cnpj == "00000000000000"))
			return false;

		Integer digitoOne = calculateDigit(cnpj.substring(0, 12), CNPJWeight);
		Integer digitoTwo = calculateDigit(cnpj.substring(0, 12) + digitoOne, CNPJWeight);
		return cnpj.equals(cnpj.substring(0, 12) + digitoOne.toString() + digitoTwo.toString());
	}
	
	private static int calculateDigit(String str, int[] weight) {
		int sum = 0;
		for (int i = str.length() - 1, digit; i >= 0; i--) {
			digit = Integer.parseInt(str.substring(i, i + 1));
			sum += digit * weight[weight.length - str.length() + i];
		}
		sum = 11 - sum % 11;
		return sum > 9 ? 0 : sum;
	}
}
