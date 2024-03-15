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
using System.Windows.Controls.Primitives;

namespace Práctica_7
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

        private void chequear(object sender, RoutedEventArgs e)
        {
            ToggleButton boton = sender as ToggleButton;
            if (boton != null)
            {
                boton.Content = "Visualizar";
                Back.Opacity = 0;
            }
        }

        private void unchequear(object sender, RoutedEventArgs e)
        {
            ToggleButton boton = sender as ToggleButton;
            if (boton != null)
            {
                boton.Content = "Borrar";
                Back.Opacity = 1;
            }
        }

        private void cambiarColor(object sender, RoutedPropertyChangedEventArgs<double> e)
        {
            Color color = Color.FromRgb((byte)SliderR.Value, (byte)SliderG.Value, (byte)SliderB.Value);
            Ventana.Background = new SolidColorBrush(color);

            ValorR.Text = "Valor = " + (int)SliderR.Value;
            ValorG.Text = "Valor = " + (int)SliderG.Value;
            ValorB.Text = "Valor = " + (int)SliderB.Value;
        }
    }
}
