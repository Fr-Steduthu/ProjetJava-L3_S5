package core.character;

public enum State {
	ALIVE, /*AWAITING_DEATH,*/ DEAD,
	STUNNED, POISONNED,
	IMMUNE_STUNNED, IMMUNE_POISONED,
	IMMUNE_ALL
	;
}
