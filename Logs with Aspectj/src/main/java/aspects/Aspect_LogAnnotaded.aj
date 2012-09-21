package aspects;

//import org.apache.log4j.Logger;
//
//import logs.Casa;

//NAO ESTA PRONTO
public aspect Aspect_LogAnnotaded {
/*
//	declare @method: * Casa.getRoomsAvailable() : @Loggable;
//
//	pointcut logDebugAnnotation(): within(@aspects.Loggable *.*);
//
//	// todos anotados
//	after(): logDebugAnnotation() {
//		Logger logger = Logger.getLogger(thisJoinPoint.getSignature()
//				.toLongString());
//		StringBuilder st = new StringBuilder();
//		Object[] arguments = thisJoinPoint.getArgs();
//		st.append("Executed ");
//		st.append(thisJoinPoint.getSignature());
//		st.append(" with the arguments: ");
//		st.append(formatArguments(arguments));
//		logger.debug(st.toString());
//	}
//
//	/**
//	 * Format Arguments
//	 * */
//	private String formatArguments(final Object[] arguments) {
//		StringBuilder buildArguments = new StringBuilder();
//		if (arguments != null && arguments.length > 0) {
//
//			for (int i = 0; i < arguments.length; i++) {
//				Object argument = arguments[i];
//				if (argument != null) {
//					buildArguments.append("[");
//					buildArguments.append(argument.getClass().getName());
//					buildArguments.append("=");
//					buildArguments.append(argument);
//					buildArguments.append("]");
//					if (i < (arguments.length - 1)) {
//						buildArguments.append(", ");
//					}
//				}
//			}
//		}
//		return buildArguments.toString();
//	}
//	*/
}