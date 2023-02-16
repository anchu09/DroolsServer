/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package threadServer;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import db.interfaces.Ans_manager;
import db.interfaces.UserManager;
import db.jdbc.JDBCManagment;
import db.jpa.JPAUserManagment;
import db.pojos.Doctor;
import db.pojos.Patient;
import db.pojos.Report;
import db.pojos.users.Role;
import db.pojos.users.User;
import javafx.collections.ObservableList;

public class ServerThreadsClient implements Runnable {

	private static UserManager userman = new JPAUserManagment();
	private static Connection c;

	private static Ans_manager inter = new JDBCManagment();
	private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

	Socket socket;

	public ServerThreadsClient(Socket socket) {
		this.socket = socket;
	}

	private static void releaseResourcesClient(PrintWriter printWriter, BufferedReader bufferedReader,
			OutputStream outputStream, InputStream inputStream, Socket socket) {
		try {
			printWriter.close();
		} catch (Exception ex) {
			Logger.getLogger(ServerThreads.class.getName()).log(Level.SEVERE, null, ex);
		}
		try {
			bufferedReader.close();
		} catch (Exception ex) {
			Logger.getLogger(ServerThreads.class.getName()).log(Level.SEVERE, null, ex);
		}

		try {
			outputStream.close();
		} catch (IOException ex) {
			Logger.getLogger(ServerThreads.class.getName()).log(Level.SEVERE, null, ex);
		}
		try {
			inputStream.close();
		} catch (IOException ex) {
			Logger.getLogger(ServerThreads.class.getName()).log(Level.SEVERE, null, ex);
		}

		try {
			socket.close();
		} catch (IOException ex) {
			Logger.getLogger(ServerThreads.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	public static void connect() {
		try {

			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:./database/ansiotunes.database");
			c.createStatement().execute("PRAGMA foreign_keys = ON");
			System.out.println("Database connection opened");
			inter.creatTables();
		} catch (SQLException E) {
			System.out.println("There was a database exception.");
			E.printStackTrace();
		} catch (Exception e) {
			System.out.println("There was a general exception.");
			e.printStackTrace();
		}

	}

	public static UserManager getUserman() {
		return userman;
	}

	public static void setUserman(UserManager userman) {
		ServerThreadsClient.userman = userman;
	}

	public static Ans_manager getInter() {
		return inter;
	}

	public static void setInter(Ans_manager inter) {
		ServerThreadsClient.inter = inter;
	}

	@Override
	public void run() {
		InputStream inputStream = null;
		BufferedReader bufferedReader = null;
		OutputStream outputStream = null;
		PrintWriter printWriter = null;
//		DataOutputStream dataOutputStream = null;
//		DataInputStream dataInputStream=null;

		try {

			getInter().connect();
//        	getUserman().connect();

		} catch (Exception e) {
			e.printStackTrace();
		}

		// CAMBIAR LOS OBJECT OUTPUTSTREAM POR EL CONTENIDO DE CADA CADA COSA QUE FORMA
		// EL OBJETO Y LUEGO EN EL CLIENTE CREO EL OBJETO CON EL CONTRUCTOR OTRA VEZ

		try {

			// CREAR Y CERRAR CADA UNO DONDE SE UTILICE Y YA
			//
			inputStream = socket.getInputStream();

			outputStream = socket.getOutputStream();

			printWriter = new PrintWriter(socket.getOutputStream(), true);

			bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

			String line;

//			line = bufferedReader.readLine();
//			System.out.println(line);
			while ((line = bufferedReader.readLine()) != null) {

				if (line.contains("stop")) {
					System.out.println("Stopping the server");
					break;
					//System.exit(0);
				} else if (line.contains("checkPassword")) {

					try {
						getInter().disconnect();

						getUserman().connect();

						String user = bufferedReader.readLine();

						String password = bufferedReader.readLine();

						User u = getUserman().checkPassword(user, password);

						if (u == null) {
							printWriter.println("null");

						} else {
							printWriter.println("acepta");

							printWriter.println(u.getRole().getName());

							printWriter.println(u.toString());
						}

						getUserman().disconnect();
						getInter().connect();
					} catch (NullPointerException ex) {
						System.out.println("ES NULL");
					}

				} else if (line.contains("getDoctorbyUser")) {
					System.out.println("getDoctorbyUser");

					User u = null;
					Doctor d = null;
					Role r = null;

					try {

						r = new Role(bufferedReader.readLine());

						u = new User(bufferedReader.readLine(), r);

						d = getInter().getDoctorbyUser(u);
						printWriter.println(d.toString());

					} catch (EOFException ex) {
						System.out.println("All data have been correctly read.");
					} catch (IOException ex) {
						System.out.println("Unable to read from the client or send client.");
						Logger.getLogger(ServerThreadsClient.class.getName()).log(Level.SEVERE, null, ex);
					}

				} else if (line.contains("getPatByUser")) {

					Patient p = null;
					Role r = null;
					try {

						r = new Role(bufferedReader.readLine());
						System.out.println("aaaaaa" + r.toString());

						User u = new User(bufferedReader.readLine(), r);
						System.out.println("aaaaaa" + u.toString());
						System.out.println("111111");
						System.out.println("zzz" + u.getId());

						p = getInter().getPatByUser(u);

						System.out.println("111111");
						System.out.println(p);
						System.out.println(p.toString());
						printWriter.println(p.toString());

						// FALTA DOCTORS

					} catch (EOFException ex) {
						System.out.println("All data have been correctly read.");
					} catch (IOException ex) {
						System.out.println("Unable to read from the client or send client.");
						Logger.getLogger(ServerThreadsClient.class.getName()).log(Level.SEVERE, null, ex);
					}

				} else if (line.contains("delete")) {
					System.out.println("delete");

					getInter().disconnect();
					getUserman().connect();
					String modifyusername = bufferedReader.readLine();
					String oldpastf = bufferedReader.readLine();

					// no se si esta list esta bien importada en los imports, si falla puede ser
					// esto
					List<Integer> userBorrar = getUserman().deleteUser(modifyusername, oldpastf);

					getUserman().disconnect();
					getInter().connect();
					getInter().deletePersonByUserId(userBorrar);
				} else if (line.contains("setOlduserDoc")) {
					System.out.println("setOlduserDoc");

					// recibo un doctor y devuelvo una string con su nombre

					Doctor d = null;
					String nombre = null;
					try {

						String textDoc = bufferedReader.readLine();

						d = new Doctor(textDoc);
						System.out.println(d.toString());
						nombre = getInter().getUserMailbydoctor(d);
						printWriter.println(nombre);

					} catch (EOFException ex) {
						System.out.println("All data have been correctly read.");
					} catch (IOException ex) {
						System.out.println("Unable to read from the client or send client.");
						Logger.getLogger(ServerThreadsClient.class.getName()).log(Level.SEVERE, null, ex);
					}
				} else if (line.contains("setOldusernamePat")) {
					System.out.println("setOldusernamePat");

					Patient p = null;
					String nombre = null;
					try {

//							recibo datos de pat
						String pText = bufferedReader.readLine();
						p = new Patient(pText);

						nombre = getInter().getUserMailbyPat(p);
						printWriter.println(nombre);

					} catch (EOFException ex) {
						System.out.println("All data have been correctly read.");
					} catch (IOException ex) {
						System.out.println("Unable to read from the client or send client.");
						Logger.getLogger(ServerThreadsClient.class.getName()).log(Level.SEVERE, null, ex);
					}
				} else if (line.contains("getAllPatientsTableDoctors")) {
					System.out.println("getAllPatientsTableDoctors");
					List<Patient> allpatients = getInter()
							.getAllPatientsTableDoctors(new Doctor(bufferedReader.readLine()));
					printWriter.println(allpatients.size() + "");
					for (int i = 0; i < allpatients.size(); i++) {
						printWriter.println(allpatients.get(i).toString());

					}

				}

				else if (line.contains("getAllDoctors")) {
					System.out.println("getAllDoctors");
					List<Doctor> alldoctors = getInter().getAllDoctors();

					printWriter.println("" + alldoctors.size());
					for (int i = 0; i < alldoctors.size(); i++) {
						printWriter.println(alldoctors.get(i).toString());

					}

				}

				else if (line.contains("ModifyPatientFromSearchDoctor")) {
					System.out.println("ModifyPatientFromSearchDoctor");

//					
//					User u= new User();
//					
//					Patient pat= new Patient();
//					
//					Doctor dselected= new Doctor();
//					
//					
//					getInter().disconnect();
//					getUserman().newUser(u);
//					getInter().connect();
//					getInter().addPatientUser(pat, u);
//					
//					
//					Patient patient=getInter().getPatByUser(u);
//					for(int i=0;i<dselected.size();i++) {
//						int id_patient=patient.getId();
//						int id_doctor=dselected.get(i).getId();
//						System.out.println("idpatient:"+id_patient+"idoctor:"+id_doctor);
//						getInter().patientDoctor(id_patient, id_doctor);
//					}

				}

				else if (line.contains("searchPatientGeneric")) {
					System.out.println("searchPatientGeneric");
					String feature = bufferedReader.readLine();
					String type = bufferedReader.readLine();
					List<Patient> result = getInter().searchPatientGeneric(feature, type);

					printWriter.println(result.size() + "");
					for (int i = 0; i < result.size(); i++) {
						printWriter.println(result.get(i).toString());

					}
				}

				else if (line.contains("searchDoctorGeneric")) {
					System.out.println("searchDoctorGeneric");
					String feature = bufferedReader.readLine();
					String type = bufferedReader.readLine();
					List<Doctor> result = getInter().searchDoctorGeneric(feature, type);

					// dataOutputStream.writeInt(result.size());
					printWriter.println("" + result.size());

					for (int i = 0; i < result.size(); i++) {
						printWriter.println(result.get(i).toString());

					}

				} else if (line.contains("getAllReportsbyPatient")) {
					System.out.println("getAllReportsbyPatient");

					Patient p = null;
					String nombre = null;
					try {

						String patText = bufferedReader.readLine();
						System.out.println(patText);

						p = new Patient(patText);

						List<String> links = getInter().getAllReportsbyPatient(p);
						System.out.println(links.size() + "");

						printWriter.println(links.size() + "");

						try {

							for (int i = 0; i < links.size(); i++) {

								printWriter.println(links.get(i));
							}

						} catch (Exception ex) {
							System.out.println("Unable to write the objects on the server.");
							Logger.getLogger(ServerThreadsClient.class.getName()).log(Level.SEVERE, null, ex);
						}

					} catch (EOFException ex) {
						System.out.println("All data have been correctly read.");
					} catch (IOException ex) {
						System.out.println("Unable to read from the client or send client.");
						Logger.getLogger(ServerThreadsClient.class.getName()).log(Level.SEVERE, null, ex);
					}
				}

				else if (line.contains("checkEmail")) {
					System.out.println("checkEmail");
					String doctoremail = bufferedReader.readLine();
					getInter().disconnect();

					getUserman().connect();
					System.out.println("doctoremail" + doctoremail);

					boolean checkemail = getUserman().checkEmail(doctoremail);
					getUserman().disconnect();

					getInter().connect();
					System.out.println(checkemail);

					printWriter.println("" + checkemail);

				} else if (line.contains("oncreateuserDoc")) {
					System.out.println("oncreateuserDoc ");
					String doctorText = bufferedReader.readLine();
					System.out.println(doctorText);

					String email = bufferedReader.readLine();
					String password = bufferedReader.readLine();

					try {
						getUserman().connect();

						getInter().disconnect();
						Role role = getUserman().getRole(1);

						MessageDigest md = MessageDigest.getInstance("MD5");
						md.update(password.getBytes());
						byte[] hash = md.digest();
						User u = new User(email, hash, role);

						getUserman().newUser(u);
						getUserman().disconnect();

						getInter().connect();

						int id = 0;
						Doctor d_new = new Doctor(doctorText);
						getInter().addDoctorUser(d_new, u);
//						Doctor dlast = getInter().getLastDoctor();

					} catch (Exception e) {
						e.printStackTrace();
					}
				} else if (line.contains("OnAcceptPatClick")) {
					System.out.println("OnAcceptPatClick");
					String email = bufferedReader.readLine();
					String password = bufferedReader.readLine();
					String name = bufferedReader.readLine();
					String song_text = bufferedReader.readLine();
					String ID = bufferedReader.readLine();
					List<Doctor> dselected = new ArrayList<Doctor>();
					int sizeList = Integer.parseInt(bufferedReader.readLine());

					for (int i = 0; i < sizeList; i++) {
						dselected.add(new Doctor(bufferedReader.readLine()));
					}
					try {
						getInter().disconnect();
						getUserman().connect();

						Role role = getUserman().getRole(2);
						MessageDigest md = MessageDigest.getInstance("MD5");
						md.update(password.getBytes());
						byte[] hash = md.digest();
						User u = new User(email, hash, role);

						getUserman().newUser(u);

						getUserman().disconnect();

						getInter().connect();

						for (int i = 0; i < dselected.size(); i++) {

							Patient p_new = new Patient(name, ID, song_text, 0, dselected.get(i).getId());
							getInter().addPatientUser(p_new, u);
						}

					} catch (Exception e) {
						e.printStackTrace();
					}

				} else if (line.contains("modifyDoctor")) {
					System.out.println("modifyDoctor");
					Doctor dmodif = null;

					String segundo = bufferedReader.readLine();
					String nuevoDato = bufferedReader.readLine();

					try {

						// oc

						String docText = bufferedReader.readLine();

						dmodif = new Doctor(docText);

						getInter().modifyDoctor(dmodif.getId(), segundo, nuevoDato);

					} catch (EOFException ex) {
						System.out.println("All data have been correctly read.");
					} catch (IOException ex) {
						System.out.println("Unable to read from the client or send client.");
						Logger.getLogger(ServerThreadsClient.class.getName()).log(Level.SEVERE, null, ex);
					}

				} else if (line.contains("modifyDPASSWORD")) {
					System.out.println("modifyDPASSWORD");

					String newpass = bufferedReader.readLine();
					String oldpastf = bufferedReader.readLine();
					String oldusername = bufferedReader.readLine();
					boolean catchdone = false;

					getInter().disconnect();
					getUserman().connect();
					catchdone = getUserman().updateUserPassword(oldusername, newpass, oldpastf, catchdone);
					getUserman().disconnect();
					getInter().connect();

					printWriter.println("" + catchdone);

				} else if (line.contains("modifyDUSER")) {
					System.out.println("modifyDUSER");

					try {
						String newUserName = bufferedReader.readLine();
						String oldUName = bufferedReader.readLine();

						getInter().disconnect();
						getUserman().connect();
						getUserman().updateUserMailWithoutpass(newUserName, oldUName);
						getUserman().disconnect();
						getInter().connect();

					} catch (Exception e) {
						e.printStackTrace();

					}
				}

				else if (line.contains("addReport")) {
					System.out.println("addReport");

					Patient pcurrent = null;

					try {

						String ptext = bufferedReader.readLine();
						int numAleatorio = Integer.parseInt(bufferedReader.readLine());
						pcurrent = new Patient(ptext);

						String linea = bufferedReader.readLine();

						String[] lista = linea.split("x");

						String s1 = "";

						System.out.println("2-gtg");

						for (int i = 0; i < lista.length; i++) {

							s1 = s1 + lista[i] + "\n";

						}
						System.out.println("2-gtg");

						System.out.println(s1);

						File f = new File("../AnsioTunesServer/reports/" + pcurrent.getName() + "_" + pcurrent.getID()
								+ "_" + numAleatorio + "_" + LocalDate.now() + ".txt");

						Report r = new Report(
								"../AnsioTunesServer/reports/" + pcurrent.getName() + "_" + pcurrent.getID() + "_"
										+ numAleatorio + "_" + LocalDate.now() + ".txt",
								Date.valueOf(LocalDate.now()), pcurrent.getId());
						System.out.println("22222");

						getInter().addReport(pcurrent, r);
						try {
							f.createNewFile();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

						// imprimir s1 en el archivo
						PrintWriter p = new PrintWriter(f);
						p.println(s1);
						p.close();
						System.out.println("7777");

					} catch (Exception e) {
						e.printStackTrace();

					}
				} else if (line.contains("leerfichero")) {
					String ruta = bufferedReader.readLine();
					String tipo = bufferedReader.readLine();
					ArrayList<Integer> datos = leerfichero(ruta, tipo);
					System.out.println("tam es" + ruta);
					printWriter.println(datos.size() + "");

					for (int i = 0; i < datos.size(); i++) {
						printWriter.println(datos.get(i) + "");

					}

				} else if (line.contains("modifyPatient")) {
					System.out.println("modifyPatient");

					Patient pmodif = null;

					try {
						String segundo = bufferedReader.readLine();
						String nuevoDato = bufferedReader.readLine();

						String patientText = bufferedReader.readLine();

						System.out.println("ññññ");
						System.out.println(patientText);
						float score = 0;
						if (segundo.contains("score")) {
							score = Float.parseFloat(nuevoDato);
						}

						pmodif = new Patient(patientText);

						getInter().modifyPatient(pmodif.getId(), segundo, nuevoDato);
						if (segundo.contains("score")) {
							System.out.println("SCORE FINISHED");
						}
					} catch (EOFException ex) {
						System.out.println("All data have been correctly read.");
					} catch (IOException ex) {
						System.out.println("Unable to read from the client or send client.");
						Logger.getLogger(ServerThreadsClient.class.getName()).log(Level.SEVERE, null, ex);
					}
				} else if (line.contains("modifyPatPassword")) {
					System.out.println("modifyPatPassword");

					String newpass = bufferedReader.readLine();
					String oldpastf = bufferedReader.readLine();
					boolean catchdone = false;
					String oldusername = bufferedReader.readLine();

					getInter().disconnect();
					getUserman().connect();
					catchdone = getUserman().updateUserPassword(oldusername, newpass, oldpastf, catchdone);
					getUserman().disconnect();
					getInter().connect();

					printWriter.println("" + catchdone);

				} else if (line.contains("modifyPatUser")) {

					System.out.println("modifyPatUser");
					String newUserName = bufferedReader.readLine();
					String oldUName = bufferedReader.readLine();
					// cuidado con los settext que hay por el medio, los tengo que conservar en el
					// cliente
					getInter().disconnect();
					getUserman().connect();

					getUserman().updateUserMailWithoutpass(newUserName, oldUName);

					getUserman().disconnect();
					getInter().connect();

				}

			}

		} catch (SocketException sex)
		{
			System.out.println("el cliente: " + " se ha cerrado.");
		}
		catch (IOException ex) {
			Logger.getLogger(ServerThreadsClient.class.getName()).log(Level.SEVERE, null, ex);
		} finally {
			getInter().disconnect();
			releaseResourcesClient(printWriter, bufferedReader, outputStream, inputStream, socket);
		}
	}

	public static ArrayList<Integer> leerfichero(String ruta, String tipo) {
		ArrayList<Integer> datos = new ArrayList<Integer>();
		BufferedReader br = null;
		try {

			FileReader fr = new FileReader(ruta);
			br = new BufferedReader(fr);

			String leido = "";
			int contador = 0;
			while (leido != null) {

				leido = br.readLine();
				if (leido.length() > 5) {
					if (tipo.compareTo("ecg") == 0) {

						String ecg = leido.substring(saberespacio(leido, 3) + 1, saberespacio(leido, 4) - 1);

						datos.add(Integer.parseInt(ecg));
					} else {

						String eda = leido.substring(saberespacio(leido, 4) + 1, saberespacio(leido, 5));

						datos.add(Integer.parseInt(eda));

					}
				}

			}

			System.out.println("he salido");

		} catch (FileNotFoundException ex1) {
			System.out.println("archivo no encontrado");
		} catch (IOException ex1) {
			System.out.println("error");
		} catch (NullPointerException ex1) {
			System.out.println("");
		} finally {
			try {
				if (br != null) {
					br.close();
				}
			} catch (Exception ex) {
				System.out.println("Error al cerrar el fichero");
				ex.printStackTrace();
			}
		}

		return datos;

	}

	public static int saberespacio(String cadena, int numeroespacio) {
		int contadorespacios = 0;
		int posicion = 0;
		boolean pasonombre = false;

		for (int i = 0; i < cadena.length(); i++) {

			if (cadena.charAt(i) == ' ') {
				contadorespacios++;

			}
			if (contadorespacios == numeroespacio) {
				break;
			}

			posicion++;
		}

		return posicion;
	}

}
