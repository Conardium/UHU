using DevExpress.Utils.CommonDialogs.Internal;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Runtime.CompilerServices;
using System.Text;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Data;
using System.Windows.Documents;
using System.Windows.Input;
using System.Windows.Media;
using System.Windows.Media.Imaging;
using System.Windows.Navigation;
using System.Windows.Shapes;

namespace PrácticaFinal_Ismael_Da_Palma_Fernández.paginas
{
    /// <summary>
    /// Lógica de interacción para paginaPrincipal.xaml
    /// </summary>
    public partial class paginaPrincipal : Page, INotifyPropertyChanged
    {
        public paginaPrincipal()
        {
            InitializeComponent();
            DataContext = this;
            fuente = 15; //Indica el tamaño de la fuente de todos los textos
            MyColorFondo = Color.FromRgb(255, 255, 255);
            MyColorFuente = Color.FromRgb(0,0,0);
            MyColorTab = Color.FromRgb(208, 208, 208);

            SliderR.Value = 255;
            SliderG.Value = 255;
            SliderB.Value = 255;
        }

        //------------------------Las variables que cambian el fondo son de tipo "Color"------------------------

        //*******************************************************************************************
        //***********************           LISTADO DE LAS PROPIEDADES        ***********************
        //*******************************************************************************************
        private int fuente;
        public int MyFuente
        {
            get { return fuente; }
            set 
            { 
                fuente = value;
                OnPropertyChanged("MyFuente");
            }
        }

        private Color colorFondo;
        public Color MyColorFondo
        {
            get { return colorFondo; }
            set 
            { 
                colorFondo = value;
                OnPropertyChanged("MyColorFondo");
            }
        }

        private Color colorFuente;

        public Color MyColorFuente
        {
            get { return colorFuente; }
            set 
            { 
                colorFuente = value;
                OnPropertyChanged("MyColorFuente");
            }
        }

        private Color colorTab;

        public Color MyColorTab
        {
            get { return colorTab; }
            set 
            { 
                colorTab = value;
                OnPropertyChanged("MyColorTab");
            }
        }

        //*******************************************************************************************
        //***********************************************************************



        //*********************************************************************************************************
        //***********************  FUNCIÓN PARA HACER FUNCIONAR CORRECTAMENTE LOS BINDINGS  ***********************
        //*********************************************************************************************************
        //INotifyPropertyChanged stuff
        public event PropertyChangedEventHandler PropertyChanged;

        //Metodo para que funcione correctamente el Data Binding
        private void OnPropertyChanged(string propertyName)
        {
            if(PropertyChanged != null) 
            {
                PropertyChanged(this, new PropertyChangedEventArgs(propertyName));
            }
        }
        //*******************************************************************************************
        //***********************************************************************



        //*******************************************************************************************
        //***********************      CONTROL DE LOS EVENTOS DEL PAGE        ***********************
        //*******************************************************************************************
        private void Salir(object sender, RoutedEventArgs e)
        {
            DialogResult resultado = (DialogResult)MessageBox.Show("¿Seguro que quieres salir?", "Volver al menú", MessageBoxButton.YesNo, MessageBoxImage.Question);
            if (resultado == DialogResult.Yes)
                NavigationService.Navigate(new paginaInicio());
        }

        private void CambiarFontSize(object sender, RoutedPropertyChangedEventArgs<double> e)
        {
            Slider slider = sender as Slider;
            if (slider != null)
            {
                textoValorFuente.Text = " " + (int)slider.Value; 
                MyFuente = (byte)slider.Value;
                OnPropertyChanged("MyFuente");
            }
        }

        private void cambiarColor(object sender, RoutedPropertyChangedEventArgs<double> e)
        {
            ValorR.Text = "" + (int)SliderR.Value;
            ValorG.Text = "" + (int)SliderG.Value;
            ValorB.Text = "" + (int)SliderB.Value;

            MyColorFondo = Color.FromRgb((byte)SliderR.Value, (byte)SliderG.Value, (byte)SliderB.Value);
            OnPropertyChanged("MyColorFondo");
        }

        //Atributos para el formulario
        private string sexo = "";
        private string estadoCivil = "";
        private string pasatiempos = "";
        private int edad = 0;

        private void ElegirEdad(object sender, RoutedPropertyChangedEventArgs<double> e)
        {
            Slider s = sender as Slider;
            if(s != null)
            {
                edad = (int)sliderEdad.Value;
                textoValorEdad.Text = " " + (int)sliderEdad.Value;
            }
        }

        private void ElegirSexo(object sender, SelectionChangedEventArgs e)
        {
            ListBox l = sender as ListBox;
            if (l != null)
            {
                switch (l.SelectedIndex)
                {
                    case 0:
                        sexo = "Hombre";
                        break;

                    case 1:
                        sexo = "Mujer";
                        break;
                }
            }
        }

        private void ElegirEstadoCivil(object sender, SelectionChangedEventArgs e)
        {
            ComboBox c = sender as ComboBox;
            if(c != null)
            {
                switch(c.SelectedIndex)
                {
                    case 0:
                        estadoCivil = "Soltero/a";
                        break;

                    case 1:
                        estadoCivil = "Casado/a";
                        break;

                    case 2:
                        estadoCivil = "Divorciado/a";
                        break;

                    case 3:
                        estadoCivil = "Viudo/a";
                        break;
                }
            }
        }

        private void Chequeado(object sender, RoutedEventArgs e)
        {
            CheckBox box = sender as CheckBox;
            if(box != null)
            {
                switch (box.Name)
                {
                    case "check1":
                        pasatiempos += "Dormir, ";
                        break;

                    case "check2":
                        pasatiempos += "Estudiar, ";
                        break;

                    case "check3":
                        pasatiempos += "Internet, ";
                        break;

                    case "check4":
                        pasatiempos += "Jugar, ";
                        break;

                    case "check5":
                        pasatiempos += "Leer, ";
                        break;

                    case "check6":
                        pasatiempos += "Películas, ";
                        break;
                }
            }
        }

        private void EnviarFormulario(object sender, RoutedEventArgs e)
        {
            if(textoRellenarNombre.Text == "" || sexo == "" || estadoCivil == "" || pasatiempos == "" || edad == 0)
                MessageBox.Show("Debe de rellenar el formulario por completo antes de poder enviarlo");
            else
            {
                MessageBox.Show("Apodo: " + textoRellenarNombre.Text + "\n\nSexo: " + sexo + "\n\nEdad: " + edad + " años" + "\n\nEstado civil: " + estadoCivil + "\n\nPasatiempos: " + pasatiempos + "\n", "Información del formulario");
                pasatiempos = "";
            }
        }

        private void CambiarTema(object sender, RoutedEventArgs e)
        {
            RadioButton r = sender as RadioButton;
            if(r != null)
            {
                string texto = (string)r.Content;
                if (texto == "Claro")
                {
                    MyColorFondo = Color.FromRgb(255, 255, 255);
                    OnPropertyChanged("MyColorFondo");
                    MyColorFuente = Color.FromRgb(0, 0, 0);
                    OnPropertyChanged("MyColorFuente");
                    MyColorTab = Color.FromRgb(208, 208, 208);
                    OnPropertyChanged("MyColorTab");

                    SliderR.Value = 255;
                    SliderG.Value = 255;
                    SliderB.Value = 255;
                    colorNegro.IsChecked = true;
                }
                
                if(texto == "Oscuro")
                {
                    MyColorFondo = Color.FromRgb(3, 26, 77);
                    OnPropertyChanged("MyColorFondo");
                    MyColorFuente = Color.FromRgb(255, 255, 255);
                    OnPropertyChanged("MyColorFuente");
                    MyColorTab = Color.FromRgb(194, 248, 249);
                    OnPropertyChanged("MyColorTab");

                    SliderR.Value = 3;
                    SliderG.Value = 26;
                    SliderB.Value = 77;
                    colorBlanco.IsChecked = true;
                }
            }
        }

        private void CambiarColorTexto(object sender, RoutedEventArgs e)
        {
            RadioButton r = sender as RadioButton;
            if (r != null)
            {
                string texto = (string)r.Content;
                if (texto == "Negro")
                    MyColorFuente = Color.FromRgb(0, 0, 0);

                if (texto == "Blanco")
                    MyColorFuente = Color.FromRgb(255, 255, 255);

                if (texto == "Amarillo")
                    MyColorFuente = Color.FromRgb(255, 255, 0);

                if (texto == "Rojo")
                    MyColorFuente = Color.FromRgb(255, 0, 0);
            }
        }

        //*******************************************************************************************
        //***********************************************************************
    }
}
