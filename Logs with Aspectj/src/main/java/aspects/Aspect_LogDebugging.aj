package aspects;

import org.apache.log4j.Logger;

import aspects.base.AbstractAspect_Log;

import com.myPackage.*;

public aspect Aspect_LogDebugging extends AbstractAspect_Log{

	// pointcuts
	pointcut publicMethodExecuted(): execution(public * *(..));

	pointcut setNumero(): call(* Casa.setNumero(int, int));

	/***
	 * Todos publicos serão logados
	 */
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
}