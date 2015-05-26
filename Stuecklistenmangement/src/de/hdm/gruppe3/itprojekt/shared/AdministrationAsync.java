package de.hdm.gruppe3.itprojekt.shared;

import java.util.Date;
import java.util.Vector;

import com.google.gwt.user.client.rpc.AsyncCallback;

import de.hdm.gruppe3.itprojekt.shared.bo.BillOfMaterial;
import de.hdm.gruppe3.itprojekt.shared.bo.Component;
import de.hdm.gruppe3.itprojekt.shared.bo.ComponentPart;
import de.hdm.gruppe3.itprojekt.shared.bo.FinishedProduct;
import de.hdm.gruppe3.itprojekt.shared.bo.User;

/**
 * The async counterpart of <code>GreetingService</code>.
 */
public interface AdministrationAsync {
	void greetServer(String input, AsyncCallback<String> callback)
			throws IllegalArgumentException;

	void init(AsyncCallback<Void> callback);

	void createUser(String firstName, String lastName, String email,
			String googleId, AsyncCallback<User> callback);

	void saveUser(User u, AsyncCallback<Void> callback);

	void deleteUser(User u, AsyncCallback<Void> callback);

	void findAllUsers(AsyncCallback<Vector<User>> callback);

	void findUserById(int id, AsyncCallback<User> callback);

	void findUserByName(String firstName, String lastName,
			AsyncCallback<User> callback);

	void createFinishedProduct(int number, String name,
			Date dateOfModifictation, AsyncCallback<FinishedProduct> callback);

	void saveFinishedProduct(FinishedProduct f, AsyncCallback<Void> callback);

	void deleteFinishedProduct(FinishedProduct f, AsyncCallback<Void> callback);

	void findAllFinishedProducts(AsyncCallback<Vector<FinishedProduct>> callback);

	void findFinishedProductById(int id, AsyncCallback<FinishedProduct> callback);

	void getComponentOf(FinishedProduct f, AsyncCallback<Component> callback);

	void getComponentPartOf(FinishedProduct f,
			AsyncCallback<ComponentPart> callback);

	void findFinishedProductByComponent(Component c,
			AsyncCallback<Vector<FinishedProduct>> callback);

	void createComponent(int number, String name, Date dateOfModifictaion,
			AsyncCallback<Component> callback);

	void saveComponent(Component c, AsyncCallback<Void> callback);

	void deleteComponent(Component c, AsyncCallback<Void> callback);

	void findAllComponents(AsyncCallback<Vector<Component>> callback);

	void findComponentById(int id, AsyncCallback<Component> callback);

	void findComponentByBom(BillOfMaterial b,
			AsyncCallback<Vector<Component>> callback);

	void findComponentByComponentPart(ComponentPart p,
			AsyncCallback<Vector<Component>> callback);

	void findComponentByComponentPartById(int id,
			AsyncCallback<Vector<Component>> callback);

	void findComponentByFinishedProduct(FinishedProduct f,
			AsyncCallback<Vector<Component>> callback);

	void getBomOfComponent(Component c,
			AsyncCallback<Vector<BillOfMaterial>> callback);

	void createComponentPart(int number, String name, String description,
			String material, AsyncCallback<ComponentPart> callback);

	void saveComponentPart(ComponentPart p, AsyncCallback<Void> callback);

	void deleteComponentPart(ComponentPart p, AsyncCallback<Void> callback);

	void findAllComponentParts(AsyncCallback<Vector<ComponentPart>> callback);

	void findComponentPartById(int id, AsyncCallback<ComponentPart> callback);

	void findComponentPart(Component c, AsyncCallback<ComponentPart> callback);

	void getComponentOfComponentPart(ComponentPart p,
			AsyncCallback<Vector<Component>> callback);

	void createBom(int id, String name, Date creationDate,
			AsyncCallback<BillOfMaterial> callback);

	void saveBom(BillOfMaterial b, AsyncCallback<Void> callback);

	void deleteBom(BillOfMaterial b, AsyncCallback<Void> callback);

	void findAllBillsOfMaterial(AsyncCallback<Vector<BillOfMaterial>> callback);

	void findBomById(int id, AsyncCallback<BillOfMaterial> callback);

	void getComponentOfBom(BillOfMaterial b,
			AsyncCallback<Vector<Component>> callback);

	void getComponentPartOfBom(BillOfMaterial b,
			AsyncCallback<Vector<ComponentPart>> callback);
}
