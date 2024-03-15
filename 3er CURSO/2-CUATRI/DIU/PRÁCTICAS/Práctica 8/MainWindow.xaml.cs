using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Data;
using System.Windows.Documents;
using System.Windows.Input;
using System.Windows.Media;
using System.Windows.Media.Imaging;
using System.Windows.Navigation;
using System.Windows.Shapes;

namespace Práctica_8
{
    /// <summary>
    /// Interaction logic for MainWindow.xaml
    /// </summary>
    public partial class MainWindow : Window
    {
        public MainWindow()
        {
            InitializeComponent();
        }

        private void ComprobarClave(object sender, RoutedEventArgs e)
        {
            Button b = sender as Button;
            if(b != null)
            {
                if(Clave.Password == "Interfaces")
                {
                    //Ventana profesor
                    Profesores p = new Profesores();
                    p.ShowDialog();
                }
                else
                {
                    //Ventana alumno
                    Alumnos a = new Alumnos();
                    a.ShowDialog();
                }
            }
        }
    }
}
