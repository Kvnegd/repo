public class QueueUnderflowException extends RuntimeException
{
	QueueUnderflowException(){super("Queue is empty");}
}