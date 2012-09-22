package aspects.base;

/**
 * Metodos comuns para os aspectos de log.
 * */
public abstract aspect AbstractAspect_Log {

	/**
	 * Format Arguments
	 * */
	protected String formatArguments(final Object[] arguments) {
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