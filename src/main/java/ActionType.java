public enum ActionType {
        add,echo,bye,list,todo,event,delete,deadline,mark,unmark;

        public static ActionType fromString(String input) {
            try {
                String[] parts = input.trim().split("\\s+");
                String action = parts[0].toLowerCase();
                ActionType actionType = ActionType.valueOf(action);
                if (parts.length > 1) {
                    actionType.argument = parts[1];
                }
                return actionType;
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException("Action impossible! Meow!");
            }
        }
        private String argument;

        public String getArgument() {
                return argument;
        }
}