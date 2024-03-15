using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

using System.Runtime.Remoting;
using System.Runtime.Remoting.Channels;
using System.Runtime.Remoting.Channels.Tcp;

namespace GestorJuegos
{
    class ServidorSudoku
    {
        static void Main(string[] args)
        {
            ChannelServices.RegisterChannel(new TcpChannel(12345), false);
            Console.WriteLine("Registrando el servicio del Sudoku Remoto en modo Singleton...");
            RemotingConfiguration.RegisterWellKnownServiceType(typeof(GestorJuegos), "GestorJuegos",
                                                                WellKnownObjectMode.Singleton);
            Console.WriteLine("Esperando llamadas Remotas...");
            Console.WriteLine("Pulsa Enter para salir..");
            Console.ReadLine();
        }
    }
}
