package aspects;

import org.apache.log4j.Logger;

import com.myPackage.*;

public aspect Aspect_Log {

	// pointcuts
	pointcut publicMethodExecuted(): execution(public * *(..));

	pointcut setNumero(): call(* Casa.setNumero(int, int));

	// todos publicos
	after(): publicMethodExecuted() {
		
		Logger logger = Logger.getLogger(thisJoinPoint.getSignature().getDeclaringType());
		StringBuilder st = new StringBuilder();
		Object[] arguments = thisJoinPoint.getArgs();
		st.append("Executed ");
		st.append(thisJoinPoint.getSignature());
		if (arguments == null || arguments.length < 1) {
			st.append(" with no arguments.");
		} else {
			st.append(" with the arguments: ");
			st.append(formatArguments(arguments));
		}
		logger.debug(st.toString());
	}

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
	
	// todos as exceptions tratadas com catch
	before(Throwable t): handler(Throwable+) && args(t){
		Logger logger = Logger.getLogger(thisJoinPoint.getSignature().getDeclaringType());
		logger.error("Handled exception: " + t.getMessage(), t);
	}

	/**
	 * Format Arguments
	 * */
	private String formatArguments(final Object[] arguments) {
		StringBuilder buildArguments = new StringBuilder();
		if (arguments != null && arguments.length > 0) {

			for (int i = 0; i < arguments.length; i++) {
				Object argument = arguments[i];
				if (argument != null) {
					buildArguments.append("[");
					buildArguments.append(argument.getClass().getName());
					buildArguments.append("=");
					buildArguments.append(argument);
					buildArguments.append("]");
					if (i < (arguments.length - 1)) {
						buildArguments.append(", ");
					}
				}
			}
		}
		return buildArguments.toString();
	}
}