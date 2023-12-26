import java.util.ArrayList;

/**
 * 
 * @author kevineguida
 *
 */

public class PasswordCheckerUtility {

	/**
	 * 
	 * @param password password string to be checked
	 * @param passwordConfirm string to be checked against password 
	 * @throws UnmatchedException thrown if not same (case sensitive)
	 */

	public static void comparePasswords(String password, String passwordConfirm) throws UnmatchedException
	{
		if (!password.equals(passwordConfirm))
			throw new UnmatchedException();
	}
	
	/**
	 * 
	 * @param password password string to be checked
	 * @param passwordConfirm string to be checked against password
	 * @return true if both the same, false otherwise (case sensitive)
	 */

	public static boolean comparePasswordsWithReturn(String password, String passwordConfirm)
	{
		if (password.equals(passwordConfirm))
		{
			return true;
		}
		else
		{
			return false;
		}

	}
	
	
	/**
	 * 
	 * @param passwords list of passwords
	 * @return ArrayList of invalid passwords in the correct format
	 */
	public static ArrayList<String> getInvalidPasswords(ArrayList<String> Passwords)
	{
		ArrayList<String> PasswordList = new ArrayList<String>();
		
		for (int i = 0; i < Passwords.size(); i++)
		{
			try
			{
				PasswordCheckerUtility.isValidPassword(Passwords.get(i));
			}
			catch (NoLowerAlphaException e)
			{
				PasswordList.add(Passwords.get(i) + " -> " + e.getMessage());
			}
			catch (InvalidSequenceException e)
			{
				PasswordList.add(Passwords.get(i) + " -> " + e.getMessage());
			}
			catch (LengthException e)
			{
				PasswordList.add(Passwords.get(i) + " -> " + e.getMessage());
			}
			catch (NoUpperAlphaException e)
			{
				PasswordList.add(Passwords.get(i) + " -> " + e.getMessage());
			}
			catch (NoSpecialCharacterException e)
			{
				PasswordList.add(Passwords.get(i) + " -> " + e.getMessage());
			}
			catch (NoDigitException e)
			{
				PasswordList.add(Passwords.get(i) + " -> " + e.getMessage());
			}	
			
		}
		return PasswordList;
	}
	
	
	/**
	 * 
	 * @param password string to be checked for
	 * @return true if password contains 6 to 9 characters, false otherwise
	 */
	public static boolean hasBetweenSixAndNineChars(String Password)
	{
		if (Password.length() >= 6 && Password.length() <= 9)
		{
			return true;
		}
		return false;

	}
	
	/**
	 * 
	 * @param password string to be checked for lowercase requirement
	 * @return true if meets lowercase requirement
	 * @throws NoLowerAlphaException thrown if does not meet lowercase requirement
	 */
	public static boolean hasLowerAlpha(String Password) throws NoLowerAlphaException

	{
		for (int i = 0; i < Password.length(); i++)
		{
			if (Password.charAt(i) >= 97 && Password.charAt(i) <= 122)
			{
				return true;
			}
		}
		throw new NoLowerAlphaException();
	}
	
	/**
	 * 
	 * @param password string to be checked for alpha character requirement
	 * @return true if meet alpha character requirement
	 * @throws NoUpperAlphaException thrown if does not meet alpha character requirement
	 */
	public static boolean hasUpperAlpha(String Password) throws NoUpperAlphaException
	{
		for (int i = 0; i < Password.length(); i++)
		{
			if (Password.charAt(i) >= 65 && Password.charAt(i) <= 90)
			{
				return true; 
			}
		}
		throw new NoUpperAlphaException();

	}
	
	
	/**
	 * 
	 * @param password string to be checked for Digit requirement
	 * @return true if meet Digit requirement
	 * @throws NoDigitException thrown if does not meet Digit requirement
	 */
	public static boolean hasDigit(String Password) throws NoDigitException
	{
		for (int i = 0; i < Password.length(); i++)
		{
			if (Password.charAt(i) >= 48 && Password.charAt(i) <= 57)
			{
				return true;
			}
		}
		throw new NoDigitException();

	}
	
	
	/**
	 * 
	 * @param password string to be checked for SpecialCharacter requirement
	 * @return true if meets SpecialCharacter requirement
	 * @throws NoSpecialCharacterException thrown if does not meet SpecialCharacter requirement
	 */
	public static boolean hasSpecialChar(String Password) throws NoSpecialCharacterException
	{
		for (int i = 0; i < Password.length(); i++)
		{
			if ((Password.charAt(i) >= 33 && Password.charAt(i) <= 47)||(Password.charAt(i) >= 58 && Password.charAt(i) <= 64)||(
				 Password.charAt(i) >= 91 && Password.charAt(i) <= 96)||(Password.charAt(i) >= 123 && Password.charAt(i) <= 126))
			{
				return true;
			}
		}
		throw new NoSpecialCharacterException();

	}
	
	
	/**
	 * 
	 * @param password string to be checked for length
	 * @return true if meets minimum length requirement
	 * @throws LengthException thrown if does not meet minimum length requirement
	 */
	public static boolean isValidLength(String Password) throws  LengthException
	{
		if (Password.length() >= 6)	
		{
			return true;
		}
		else
		{
			throw new LengthException();
		}

	}
	
	
	/**
	 * 
	 * @param password string to be checked for Sequence requirement
	 * @return false if does NOT meet Sequence requirement
	 * @throws InvalidSequenceException thrown if meets Sequence requirement
	 */
	public static boolean noSameCharInSequence(String Password) throws InvalidSequenceException
	{
		for (int i = 0; i < Password.length()-2; i++)
		{
			if (Password.charAt(i) == Password.charAt(i+1) &&  Password.charAt(i) == Password.charAt(i+2))
			{
				throw new InvalidSequenceException();
			}
		}
		return true;

	}

	
	/**
	 * 
	 * @param password string to be checked for validity
	 * @return true if valid password, false if an invalid password
	 * @throws LengthException thrown if length is less than 6 characters
	 * @throws NoUpperAlphaException thrown if no uppercase alphabetic
	 * @throws NoLowerAlphaException thrown if no lowercase alphabetic
	 * @throws NoDigitException thrown if no digit
	 * @throws NoSpecialCharacterException thrown if does not meet SpecialCharacter requirement
	 * @throws InvalidSequenceException thrown if more than 2 of same character.
	 */
	public static boolean isValidPassword(String Password) throws LengthException, NoUpperAlphaException, 
														   NoLowerAlphaException, NoDigitException, 
														   NoSpecialCharacterException, InvalidSequenceException
	{
		if (PasswordCheckerUtility.isValidLength(Password))
		{
			if (PasswordCheckerUtility.hasDigit(Password))
			{
				if (PasswordCheckerUtility.hasUpperAlpha(Password))
				{
					if (PasswordCheckerUtility.hasLowerAlpha(Password))
					{
						if (PasswordCheckerUtility.hasSpecialChar(Password))
						{
							if (PasswordCheckerUtility.noSameCharInSequence(Password))
							{
								return true;
							}
							else throw new InvalidSequenceException();
						} 
						else throw new NoSpecialCharacterException();
					} 
					else throw new NoLowerAlphaException();
				} 
				else throw new NoUpperAlphaException();
			} 
			else throw new NoDigitException();
		}
		else throw new LengthException();

	}
	
	
	/**
	 * 
	 * @param password string to be checked if weak password
	 * @return false if the password is valid and the length of password is NOT between 6 and 9 (inclusive).
	 * @throws WeakPasswordException if length of password is between 6 and 9 (inclusive), ALTHOUGH the password may be VALID.
	 */
	public static boolean isWeakPassword(String Password) throws WeakPasswordException
	{
		boolean validPassword;
		boolean sixToNine;
		
		try 
		{
			validPassword = (boolean)PasswordCheckerUtility.isValidPassword(Password);
			sixToNine = PasswordCheckerUtility.hasBetweenSixAndNineChars(Password);
		} 
		catch(Exception e) 
		{
			return false;
		}

		if (validPassword && !sixToNine)
			return false;
		else
			throw new WeakPasswordException();

	}

}
