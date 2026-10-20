public class TransferService {

    public void transfer(BankAccount from, BankAccount to, double amount)
            throws BankingException, InsufficientFundsException,
            TransferLimitExceededException, InvalidAccountException {
        if (amount <= 0) {
            throw new BankingException("Le montant du transfert doit être supérieur à zéro.");
        }
        
        if (from == null || to == null) {
            throw new InvalidAccountException("Les comptes source et destination ne peuvent pas être nuls.");
        }

        String fromAccountNumber = from.getAccountNumber();
        String toAccountNumber = to.getAccountNumber();
        if (fromAccountNumber == null || toAccountNumber == null
                || fromAccountNumber.equals(toAccountNumber)) {
            throw new InvalidAccountException(
                    "Les comptes doivent avoir un numéro valide et être différents.");
        }

        if (amount > 10000) {
            throw new TransferLimitExceededException(
                    "Plafond de transfert dépassé. Le montant maximum autorisé est de 10 000.");
        }

        if (from.getBalance() < amount) {
            throw new InsufficientFundsException("Fonds insuffisants sur le compte source.");
        }

        from.debit(amount);
        to.credit(amount);
    }

}
