package day9.intcode

class EqualsInstruction: Instruction {
    override suspend fun execute(positions: ProgramPositions, memory: Memory): InstructionResponse {
        val currentPosition = positions.currentPosition
        val relativeBase = positions.relativeBase
        val parameterModes = getParameterModes(memory.get(currentPosition))
        val firstParameterValue = getParameterValue(memory.get(currentPosition + 1), relativeBase, parameterModes[0], memory)
        val secondParameterValue = getParameterValue(memory.get(currentPosition + 2), relativeBase, parameterModes[1], memory)
        val destination = memory.getOutputDestination(currentPosition + 3, relativeBase, parameterModes[2])
        if(firstParameterValue == secondParameterValue) {
            println("Equals: Writing 1 to $destination")
            memory.set(destination, 1)
        } else {
            println("Equals: Writing 0 to $destination")
            memory.set(destination, 0)
        }
        return InstructionResponse(ProgramPositions(currentPosition + 4, relativeBase), memory)
    }
}