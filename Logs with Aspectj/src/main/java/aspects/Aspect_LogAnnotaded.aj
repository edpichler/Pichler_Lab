package aspects;

import org.apache.log4j.Logger;

import aspects.base.AbstractAspect_Log;

import com.myPackage.Casa;

public aspect Aspect_LogAnnotaded extends AbstractAspect_Log{

	declare @method: * Casa.getRoomsAvailable() : @Loggable;

	pointcut logDebugAnnotation(): 
		execution(@Loggable * *.*(..));

	// todos anotados
	after(): logDebugAnnotation() {
		System.out.println("Chamou getRoomsAvailable");
		
		Logger logger = Logger.getLogger(thisJoinPoint.getSignature()
				.toLongString());
		StringBuilder st = new StringBuilder();
		Object[] arguments = thisJoinPoint.getArgs();
		st.append("Executed ");
		st.append(thisJoinPoint.getSignature());
		st.append(" with the arguments: ");
		st.append(formatArguments(arguments));
		logger.debug(st.toString());
	}
}