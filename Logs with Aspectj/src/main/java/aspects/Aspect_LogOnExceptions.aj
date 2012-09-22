package aspects;

import org.apache.log4j.Logger;

import aspects.base.AbstractAspect_Log;

import com.myPackage.*;

public  aspect Aspect_LogOnExceptions extends AbstractAspect_Log{

	// pointcuts
	pointcut setNumero(): call(* Casa.setNumero(int, int));

	/**
	 * Logar se o poincut setNumero lançar exceção
	 * */
	after() throwing(Throwable t) : setNumero() {
		
		Logger logger = Logger.getLogger(thisJoinPoint.getSignature().getDeclaringType());
		StringBuilder builder = new StringBuilder();
		String message = t.getMessage();
		if (message != null) {
			builder.append(message);
			builder.append(" ");
		}
		builder.append("The arguments are: "
				+ formatArguments(thisJoinPoint.getArgs()));
		logger.error(builder.toString(), t);
	}
	
	/**
	 * Logar se o Main.main tratar uma exceção.
	 * */
	before(Throwable t): handler(Throwable+) && args(t) 
			&& withincode(* Main.main(..)) {
		Logger logger = Logger.getLogger(thisJoinPoint.getSignature().getDeclaringType());
		logger.error("Handled exception: " + t.getMessage(), t);
	}

	/**
	 * Logar todos os métodos que lançarem uma exceção.
	 * */
	before(Throwable t): handler(Throwable+) && args(t) {
		Logger logger = Logger.getLogger(thisJoinPoint.getSignature().getDeclaringType());
		logger.error("Handled exception: " + t.getMessage(), t);
	}
}