import sun.invoke.empty.Empty;

public class GenericQueue<T>
{
   private int size;
   private QueueNode front;
   private QueueNode back;
   public GenericQueue()
   {
      size = 0;
      front = null;
      back = null;
   }

   public void enqueue(T data)
   {
      QueueNode addNode = new QueueNode(data);
      if (front == null)
      {
         front = addNode;
      }
      if (back == null)
      {
         back = addNode;
         size++;
      }
      else
      {
         back.next = addNode;
         back = addNode;
         size++;
      }
   }

   public T dequeue() throws EmptyQueueException
   {
      if (!isEmpty())
      {
         QueueNode temp = front;
         front = front.next;
         if (front == null)
         {
            back = null;
         }
         size--;
         return (T) temp.data;
      }
      else throw new EmptyQueueException();
   }

   public T peek() throws EmptyQueueException
   {
      if (!isEmpty())
      {
         return (T) front.data;
      }
      else throw new EmptyQueueException();
   }

   public boolean isEmpty()
   {
      return size == 0;
   }
   public String toString()
   {
      String retString = "";

      for (QueueNode node = front; node != null; node = node.next)
      {
         retString += node.data;
         if (node.next != null)
         {
            retString += " ";
         }
      }
      return retString;
   }
   public int getSize() {return size;}

   private class QueueNode<T>
   {
      T data;
      QueueNode next = null;
      QueueNode(T data)
      {
         this.data = data;
         next = null;
      }
   }

   public static void main(String[] args)
   {
      GenericQueue<Integer> queue = new GenericQueue<>();
      for (int i = 0; i < 100; i++)
      {
         queue.enqueue(i);
      }
      System.out.print("Peek: " );
      try
      {
         System.out.println(queue.peek());
      }
      catch (EmptyQueueException e)
      {
         System.out.println(e);
      }
      System.out.println("Current queue front to back:\n" + queue.toString());
      System.out.println("Queue size: " + queue.getSize());

      //force emptyQueue exception
      while (queue.getSize() >= 0)
      {
         try
         {
            System.out.println("dequeue: " + queue.dequeue() + " queue size: " + queue.getSize());
         }
         catch (EmptyQueueException e)
         {
            System.out.println(e);
            break;
         }
      }
   }
}

class EmptyQueueException extends Exception
{
   public EmptyQueueException(){}

   @Override
   public String toString()
   {
      return "Error: Queue empty.";
   }
}