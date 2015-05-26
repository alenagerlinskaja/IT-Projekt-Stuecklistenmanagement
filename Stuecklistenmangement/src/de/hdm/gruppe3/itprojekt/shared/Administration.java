package de.hdm.gruppe3.itprojekt.shared;

import java.util.Date;
import java.util.Vector;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import de.hdm.gruppe3.itprojekt.shared.bo.BillOfMaterial;
import de.hdm.gruppe3.itprojekt.shared.bo.Component;
import de.hdm.gruppe3.itprojekt.shared.bo.ComponentPart;
import de.hdm.gruppe3.itprojekt.shared.bo.FinishedProduct;
import de.hdm.gruppe3.itprojekt.shared.bo.User;

/**
 * The client-side stub for the RPC service.
 */
@RemoteServiceRelativePath("administration")
public interface Administration extends RemoteService {
	String greetServer(String name) throws IllegalArgumentException;

	public void init() throws IllegalArgumentException;

	/**
	 * Einen User anlegen.
	 * 
	 * @param firstName
	 * @param lastName
	 * @param email
	 * @param googleId
	 * @return
	 * @throws IllegalArgumentException
	 */

	public User createUser(String firstName, String lastName, String email,
			String googleId) throws IllegalArgumentException;

	public void saveUser(User u) throws IllegalArgumentException;

	public void deleteUser(User u) throws IllegalArgumentException;

	public Vector<User> findAllUsers() throws IllegalArgumentException;

	public User findUserById(int id) throws IllegalArgumentException;

	public User findUserByName(String firstName, String lastName)
			throws IllegalArgumentException;

	/**
	 * Ein Enderzeugnis anlegen.
	 * 
	 * @param id
	 * @param name
	 * @param dateOfModifictation
	 * @return Ein fertiges Enderzeugnis-Objekt.
	 * @throws IllegalArgumentException
	 */

	public FinishedProduct createFinishedProduct(int id, String name,
			Date dateOfModifictation) throws IllegalArgumentException;

	public void saveFinishedProduct(FinishedProduct f)
			throws IllegalArgumentException;

	public void deleteFinishedProduct(FinishedProduct f)
			throws IllegalArgumentException;

	public Vector<FinishedProduct> findAllFinishedProducts()
			throws IllegalArgumentException;

	public FinishedProduct findFinishedProductById(int id)
			throws IllegalArgumentException;

	public Component getComponentOf(FinishedProduct f)
			throws IllegalArgumentException;

	public ComponentPart getComponentPartOf(FinishedProduct f)
			throws IllegalArgumentException;

	public Vector<FinishedProduct> findFinishedProductByComponent(Component c)
			throws IllegalArgumentException;

	/**
	 * Eine Baugruppe anlegen.
	 * 
	 * @param number
	 * @param name
	 * @param dateOfModifictaion
	 * @return Ein fertiges Baugruppen-Objekt.
	 * @throws IllegalArgumentException
	 */
	public Component createComponent(int number, String name,
			Date dateOfModifictaion) throws IllegalArgumentException;

	public void saveComponent(Component c) throws IllegalArgumentException;

	public void deleteComponent(Component c) throws IllegalArgumentException;

	public Vector<Component> findAllComponents()
			throws IllegalArgumentException;

	public Component findComponentById(int id) throws IllegalArgumentException;

	public Vector<Component> findComponentByBom(BillOfMaterial b)
			throws IllegalArgumentException;

	public Vector<Component> findComponentByComponentPart(ComponentPart p)
			throws IllegalArgumentException;

	public Vector<Component> findComponentByComponentPartById(int id)
			throws IllegalArgumentException;

	public Vector<Component> findComponentByFinishedProduct(FinishedProduct f)
			throws IllegalArgumentException;

	public Vector<BillOfMaterial> getBomOfComponent(Component c)
			throws IllegalArgumentException;

	/**
	 * Ein Bauteil anlegen.
	 * 
	 * @param number
	 * @param name
	 * @param description
	 * @param material
	 * @return Ein fertiges Bauteil-Objekt.
	 * @throws IllegalArgumentException
	 */
	public ComponentPart createComponentPart(int number, String name,
			String description, String material)
			throws IllegalArgumentException;

	public void saveComponentPart(ComponentPart p)
			throws IllegalArgumentException;

	public void deleteComponentPart(ComponentPart p)
			throws IllegalArgumentException;

	public Vector<ComponentPart> findAllComponentParts()
			throws IllegalArgumentException;

	public ComponentPart findComponentPartById(int id)
			throws IllegalArgumentException;

	public ComponentPart findComponentPart(Component c)
			throws IllegalArgumentException;

	public Vector<Component> getComponentOfComponentPart(ComponentPart p)
			throws IllegalArgumentException;

	/**
	 * Eine Stueckliste anlegen.
	 * 
	 * @param id
	 * @param name
	 * @param creationDate
	 * @return Ein fertiges Stuecklisten-Objekt.
	 * @throws IllegalArgumentException
	 */
	public BillOfMaterial createBom(int id, String name, Date creationDate)
			throws IllegalArgumentException;

	public void saveBom(BillOfMaterial b) throws IllegalArgumentException;

	public void deleteBom(BillOfMaterial b) throws IllegalArgumentException;

	public Vector<BillOfMaterial> findAllBillsOfMaterial()
			throws IllegalArgumentException;

	public BillOfMaterial findBomById(int id) throws IllegalArgumentException;

	public Vector<Component> getComponentOfBom(BillOfMaterial b)
			throws IllegalArgumentException;

	public Vector<ComponentPart> getComponentPartOfBom(BillOfMaterial b)
			throws IllegalArgumentException;

}
