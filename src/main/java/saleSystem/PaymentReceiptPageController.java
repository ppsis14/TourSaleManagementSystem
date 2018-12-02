package saleSystem;

import Table.Receipt;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Optional;
import java.util.ResourceBundle;

import static saleSystem.SaleManagementUtil.*;

public class PaymentReceiptPageController implements Initializable {

    @FXML private ComboBox<String> tourIDChoiceDR;
    @FXML private ComboBox<String> tourIDChoiceAR;
    @FXML private TableView<Receipt> depositReceiptTable;
    @FXML private TableColumn<Receipt, String> reservationCodeColumnDR;
    @FXML private TableColumn<Receipt, String> depositReceipt_no_ColumnDR;
    @FXML private TableColumn<Receipt, Integer> amountColumnDR;
    @FXML private TableColumn<Receipt, String> employeeNameColumnDR;
    @FXML private TableColumn<Receipt, String> depositReceiptStatusColumnDR;

    @FXML private TableView<Receipt> arrearsReceiptTable;
    @FXML private TableColumn<Receipt, String> reservationCodeColumnAR;
    @FXML private TableColumn<Receipt, String> depositReceipt_no_ColumnAR;
    @FXML private TableColumn<Receipt, Integer> amountColumnAR;
    @FXML private TableColumn<Receipt, String> employeeNameColumnAR;
    @FXML private TableColumn<Receipt, String> depositReceiptStatusColumnAR;
    @FXML private JFXButton createDepositReceiptBtn;
    @FXML private JFXButton confirmDepositReceiptStatusBtn;
    @FXML private JFXButton createArrearsReceiptBtn;
    @FXML private JFXButton confirmArrearsReceiptStatusBtn;
    @FXML private JFXHamburger menu;
    @FXML private JFXDrawer drawerMenu;
    ObservableList<Receipt> obListReceiptDR = FXCollections.observableArrayList();
    ObservableList<Receipt> obListReceiptAR = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        SaleManagementUtil.initDrawerToolBar(drawerMenu, menu, getClass().getResource("/hamburgerMenu.fxml"));
        SaleManagementUtil.setTourID(tourIDChoiceDR);
        SaleManagementUtil.setTourID(tourIDChoiceAR);
        showTableView();
    }

    @FXML
    void handleConfirmArrearsReceiptStatusBtn(ActionEvent event) {

    }

    @FXML
    void handleConfirmDepositReceiptStatusBtn(ActionEvent event) {

    }

    public void showTableView(){

        String tourNameDR = tourIDChoiceDR.getSelectionModel().getSelectedItem();
        String tourNameAR = tourIDChoiceAR.getSelectionModel().getSelectedItem();
        obListReceiptDR = FXCollections.observableArrayList(manageableDatabase.getAllReceiptInTourName(DEPOSIT_RECEIPT,tourNameDR));
        obListReceiptAR = FXCollections.observableArrayList(manageableDatabase.getAllReceiptInTourName(ARREARS_RECEIPT,tourNameAR));

        //find data base for show on table view.
        reservationCodeColumnDR.setCellValueFactory(new PropertyValueFactory<>("reservationCode"));
        depositReceipt_no_ColumnDR.setCellValueFactory(new PropertyValueFactory<>("receiptNo"));
        amountColumnDR.setCellValueFactory(new PropertyValueFactory<>("amountCustomer"));
        employeeNameColumnDR.setCellValueFactory(new PropertyValueFactory<>("employeeName"));
        depositReceiptStatusColumnDR.setCellValueFactory(new PropertyValueFactory<>("receiptStatus"));

        reservationCodeColumnAR.setCellValueFactory(new PropertyValueFactory<>("reservationCode"));
        depositReceipt_no_ColumnAR.setCellValueFactory(new PropertyValueFactory<>("invoiceNo"));
        amountColumnAR.setCellValueFactory(new PropertyValueFactory<>("amountCustomer"));
        employeeNameColumnAR.setCellValueFactory(new PropertyValueFactory<>("employeeName"));
        depositReceiptStatusColumnAR.setCellValueFactory(new PropertyValueFactory<>("receiptStatus"));

        depositReceiptTable.setItems(obListReceiptDR);
        arrearsReceiptTable.setItems(obListReceiptAR);
    }

    @FXML
    void handleCreateArrearsReceiptBtn(ActionEvent event) {
        Alert alertCreateArrearsReceipt = new Alert(Alert.AlertType.CONFIRMATION);
        alertCreateArrearsReceipt.setTitle("Confirmation Dialog");
        alertCreateArrearsReceipt.setHeaderText(null);
        alertCreateArrearsReceipt.setContentText("Do you want to create receipt?");
        Optional<ButtonType> createArrearsReceiptAction = alertCreateArrearsReceipt.showAndWait();

        if (createArrearsReceiptAction.get() == ButtonType.OK){
            // create receipt
            createReceipt("thikampornDepositReceipt", "RECEIPT / ใบสำคัญรับเงิน");
            Alert alertShowCreateArrearsReceipt = new Alert(Alert.AlertType.INFORMATION);
            alertShowCreateArrearsReceipt.setTitle("Information Dialog");
            alertShowCreateArrearsReceipt.setHeaderText(null);
            alertShowCreateArrearsReceipt.setContentText("Creating receipt is successfully!");
            Optional<ButtonType> showCreateArrearsReceiptAction = alertShowCreateArrearsReceipt.showAndWait();
            if (showCreateArrearsReceiptAction.get() == ButtonType.OK){
                // update database and table code
            }

        }

    }

    @FXML
    void handleCreateDepositReceiptBtn(ActionEvent event) {
        Alert alertCreateDepositReceipt = new Alert(Alert.AlertType.CONFIRMATION);
        alertCreateDepositReceipt.setTitle("Confirmation Dialog");
        alertCreateDepositReceipt.setHeaderText(null);
        alertCreateDepositReceipt.setContentText("Do you want to create deposit receipt?");
        Optional<ButtonType> createDepositReceiptAction = alertCreateDepositReceipt.showAndWait();
        if (createDepositReceiptAction.get() == ButtonType.OK){
            // create receipt
            createReceipt("thikampornReceipt", "DEPOSIT RECEIPT / ใบเสร็จรับเงิน");
            Alert alertShowCreateDepositReceipt = new Alert(Alert.AlertType.INFORMATION);
            alertShowCreateDepositReceipt.setTitle("Information Dialog");
            alertShowCreateDepositReceipt.setHeaderText(null);
            alertShowCreateDepositReceipt.setContentText("Creating receipt is successfully!");
            Optional<ButtonType> showCreateDepositReceiptAction = alertShowCreateDepositReceipt.showAndWait();
            if (showCreateDepositReceiptAction.get() == ButtonType.OK){
                // update database and table code
            }


        }
    }

    @FXML
    void handleTourIDChoiceAR(ActionEvent event) {
        showTableView();
    }

    @FXML
    void handleTourIDChoiceDR(ActionEvent event) {
        showTableView();
    }


    private void createReceipt(String fileName, String titleReceipt) {
        Document document = new Document();
        document.setPageSize(PageSize.A4);

        Calendar calendar = new GregorianCalendar();
        String currentDate = String.valueOf(calendar.get(Calendar.DAY_OF_MONTH)) + "/" + String.valueOf(calendar.get(Calendar.MONTH)) + "/" + String.valueOf(calendar.get(Calendar.YEAR)+543);

        try {
            //  create font
            Font angsanaNewFont18Bold = new Font(BaseFont.createFont("fonts/Angsa.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED), 18, Font.BOLD, BaseColor.BLACK);
            Font angsanaNewFont16 = new Font(BaseFont.createFont("fonts/Angsa.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED), 16, Font.NORMAL, BaseColor.BLACK);
            Font angsanaNewFont16Bold = new Font(BaseFont.createFont("fonts/Angsa.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED), 16, Font.BOLD, BaseColor.BLACK);
            Font angsanaNewFont14 = new Font(BaseFont.createFont("fonts/Angsa.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED), 14, Font.NORMAL, BaseColor.BLACK);

            // file name pattern -->  invoice type(DI/AI)-reserve id-customer id ex. DI-21805-0003
            PdfWriter.getInstance(document, new FileOutputStream(fileName + ".pdf"));

            document.open();

            // create table
            PdfPTable compDetailTable = new PdfPTable(2);
            compDetailTable.setTotalWidth(500);
            compDetailTable.setLockedWidth(true);
            compDetailTable.setWidths(new float[]{20, 30});
            compDetailTable.setHorizontalAlignment(Element.ALIGN_CENTER);
            compDetailTable.getDefaultCell().setBorderColor(new BaseColor(255,255,255));
            // the cell object
            PdfPCell compCell;
            // now we add a cell with rowSpan 2
            compCell = new PdfPCell(new Phrase("Cell with rowspan 2"));
            compCell.setRowspan(4);
            compCell.setPaddingLeft(10);
            compCell.setBorderColor(new BaseColor(255,255,255));
            Image image = Image.getInstance("./src/main/resources/images/logo2.png");
            compCell.addElement(image);
            compCell.setPaddingTop(9);
            compCell.setPaddingRight(8);
            compDetailTable.addCell(compCell);
            // we add the four remaining cells with addCell()
            compDetailTable.addCell(new Paragraph("ON VACATION TRAVELING CO., LTD.", angsanaNewFont16Bold));
            compDetailTable.addCell(new Paragraph("บริษัท ออนเวเคชั่น จำกัด", angsanaNewFont14));
            compDetailTable.addCell(new Paragraph("23/2 ชั้น 1 ถ.สุขุมวิท 23 (อโศก) แขวงคลองเตยเหนือ เขตวัฒนา กทม. 10110", angsanaNewFont14));
            compDetailTable.addCell(new Paragraph("TEL : (66)2 910-1043      FAX : (66)2 910-1044", angsanaNewFont14));
            document.add(compDetailTable);

            document.add(new Phrase("\n"));
            Paragraph titleName = new Paragraph(titleReceipt, angsanaNewFont16);
            titleName.setAlignment(Paragraph.ALIGN_CENTER);
            document.add(titleName);
            //document.add(new Phrase("\n"));

            // create table
            PdfPTable invoiceTable = new PdfPTable(2);
            invoiceTable.setWidthPercentage(30);
            invoiceTable.setHorizontalAlignment(Element.ALIGN_RIGHT);
            invoiceTable.setWidths(new float[] {1f, 2f});
            invoiceTable.getDefaultCell().setPaddingTop(10);
            invoiceTable.addCell(createCell("No.", angsanaNewFont16, 0.5f, 1, Element.ALIGN_LEFT));
            invoiceTable.addCell(createCell("I-S21805-003", angsanaNewFont16, 0.5f, 1, Element.ALIGN_LEFT));
            invoiceTable.addCell(createCell("Date", angsanaNewFont16, 0.5f, 1, Element.ALIGN_LEFT));
            invoiceTable.addCell(createCell(currentDate, angsanaNewFont16, 0.5f, 1, Element.ALIGN_LEFT));
            invoiceTable.addCell(createCell("Due Date", angsanaNewFont16, 0.5f, 1, Element.ALIGN_LEFT));
            invoiceTable.addCell(createCell("05/12/2561", angsanaNewFont16, 0.5f, 1, Element.ALIGN_LEFT));
            document.add(invoiceTable);

            PdfPTable detailTable = new PdfPTable(6);
            detailTable.setTotalWidth(500);
            detailTable.setLockedWidth(true);
            detailTable.setWidths(new float[]{3, 0.5f, 5, 2, 0.5f, 5});
            detailTable.setHorizontalAlignment(Element.ALIGN_CENTER);
            detailTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
            detailTable.getDefaultCell().setPaddingBottom(2);
            detailTable.addCell(createCell("Reservation ID", angsanaNewFont16, 0,1 , Element.ALIGN_LEFT));
            detailTable.addCell(createCell(":", angsanaNewFont16, 0, 1, Element.ALIGN_LEFT));
            detailTable.addCell(createCell("00003", angsanaNewFont16, 0, 1, Element.ALIGN_LEFT));
            detailTable.addCell(createCell("Tour ID", angsanaNewFont16, 0 ,1, Element.ALIGN_LEFT));
            detailTable.addCell(createCell(":", angsanaNewFont16, 0, 1, Element.ALIGN_LEFT));
            detailTable.addCell(createCell("TW3D2N", angsanaNewFont16, 0, 1, Element.ALIGN_LEFT));
            detailTable.addCell(createCell("Customer Name", angsanaNewFont16, 0 ,1, Element.ALIGN_LEFT));
            detailTable.addCell(createCell(":", angsanaNewFont16,0 ,1, Element.ALIGN_LEFT));
            detailTable.addCell(createCell("คุณฑิฆัมพร", angsanaNewFont16, 0 ,1, Element.ALIGN_LEFT));
            detailTable.addCell(createCell("Tel./Fax", angsanaNewFont16, 0 ,1, Element.ALIGN_LEFT));
            detailTable.addCell(createCell(":", angsanaNewFont16, 0 ,1, Element.ALIGN_LEFT));
            detailTable.addCell(createCell("0927606499", angsanaNewFont16, 0 ,1, Element.ALIGN_LEFT));
            detailTable.addCell(createCell("Customer ID", angsanaNewFont16, 0 ,1, Element.ALIGN_LEFT));
            detailTable.addCell(createCell(":", angsanaNewFont16, 0 ,1, Element.ALIGN_LEFT));
            detailTable.addCell(createCell("SDC134", angsanaNewFont16, 0 ,1, Element.ALIGN_LEFT));
            detailTable.addCell(createCell("Salesman", angsanaNewFont16, 0 ,1, Element.ALIGN_LEFT));
            detailTable.addCell(createCell(":", angsanaNewFont16, 0 ,1, Element.ALIGN_LEFT));
            detailTable.addCell(createCell("นายพลพล อินทร์งาม", angsanaNewFont16, 0 ,1, Element.ALIGN_LEFT));
            document.add(detailTable);
            document.add(new Phrase("\n"));

            PdfPTable descriptionTable = new PdfPTable(5);
            descriptionTable.setTotalWidth(500);
            descriptionTable.setLockedWidth(true);
            descriptionTable.setWidths(new float[]{2, 9, 3, 4, 4});
            descriptionTable.setHorizontalAlignment(Element.ALIGN_CENTER);
            descriptionTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
            descriptionTable.addCell(createCell("Item (s)\n ลำดับที่", angsanaNewFont16, 0.5f, 1, Element.ALIGN_CENTER));
            descriptionTable.addCell(createCell("Description (s)\nรายการสินค้า", angsanaNewFont16, 0.5f, 1, Element.ALIGN_CENTER));
            descriptionTable.addCell(createCell("Quantity\nจำนวน", angsanaNewFont16, 0.5f, 1 , Element.ALIGN_CENTER));
            descriptionTable.addCell(createCell("Unit Price\nราคาต่อหน่วย", angsanaNewFont16, 0.5f, 1, Element.ALIGN_CENTER));
            descriptionTable.addCell(createCell("Amount (Baht)\nจำนวนเงิน (บาท)",angsanaNewFont16, 0.5f, 1, Element.ALIGN_CENTER));
            descriptionTable.addCell(createCell("1" , angsanaNewFont16, 0.5f, 1, Element.ALIGN_CENTER));
            descriptionTable.addCell(createCellFixHeight("ยอดมัดจำค่าบริการ โปรแกรม LEELA GRAND BEIJING 26 - 30 SEP 2018 สำหรับ 2 ท่าน", angsanaNewFont16, 0.5f, 1, Element.ALIGN_LEFT, 200));
            descriptionTable.addCell(createCell("2", angsanaNewFont16, 0.5f, 1, Element.ALIGN_CENTER));
            descriptionTable.addCell(createCell("20,000.00", angsanaNewFont16, 0.5f, 1, Element.ALIGN_RIGHT));
            descriptionTable.addCell(createCell("40,000.00", angsanaNewFont16, 0.5f, 1, Element.ALIGN_RIGHT));
            descriptionTable.addCell(createCell("   Baht     :   (สี่หมื่นบาทถ้วน)", angsanaNewFont16, 0.5f, 3, Element.ALIGN_LEFT));
            descriptionTable.addCell(createCell("   Total", angsanaNewFont16, 0.5f, 1, Element.ALIGN_LEFT));
            descriptionTable.addCell(createCell("40,000.00", angsanaNewFont16, 0.5f, 1, Element.ALIGN_RIGHT));
            document.add(descriptionTable);

            PdfPTable remarkTable = new PdfPTable(3);
            remarkTable.setTotalWidth(500);
            remarkTable.setLockedWidth(true);
            remarkTable.setWidths(new float[]{2, 1, 20});
            remarkTable.setHorizontalAlignment(Element.ALIGN_CENTER);
            remarkTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
            remarkTable.addCell(createCell("Remark", angsanaNewFont16, 0f, 1, Element.ALIGN_LEFT));
            remarkTable.addCell(createCell(":", angsanaNewFont16, 0f, 1, Element.ALIGN_LEFT));
            remarkTable.addCell(createCell("ราคาข้างต้นไม่รวมค่าภาษีมูลค่าเพิ่ม 7%", angsanaNewFont16, 0f, 1, Element.ALIGN_LEFT));
            remarkTable.addCell(createCell(" ", angsanaNewFont16, 0f, 1, Element.ALIGN_LEFT));
            remarkTable.addCell(createCell(" ", angsanaNewFont16, 0f, 1, Element.ALIGN_LEFT));
            remarkTable.addCell(createCell(" ", angsanaNewFont16, 0f, 1, Element.ALIGN_LEFT));    remarkTable.addCell(createCell(" ", angsanaNewFont16, 0f, 1, Element.ALIGN_LEFT));
            remarkTable.addCell(createCell(" ", angsanaNewFont16, 0f, 1, Element.ALIGN_LEFT));
            remarkTable.addCell(createCell("กรูณาโอนเงิน ชื่อบัญชี บริษัท อนนเวเคชั่น จำกัด\nธนาคารกสิกรไทย สาขาสุขุมวิท 23 บัญชีกระแสรายวัน เลขที่ xxx-x-xxxxx-x\nหลังการโอนกรูณาแฟกซ์ใบนำฝาก (PAY IN) มาที่เบอร์ 02-910-1998", angsanaNewFont16, 0f, 1, Element.ALIGN_LEFT));
            document.add(remarkTable);
            document.add(new Phrase(" "));

            // create table for signatures
            PdfPTable signatureTable = new PdfPTable(3);
            signatureTable.setTotalWidth(500);
            signatureTable.setLockedWidth(true);
            signatureTable.setWidths(new float[]{20, 20, 20});
            signatureTable.setHorizontalAlignment(Element.ALIGN_CENTER);
            signatureTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
            signatureTable.addCell(createCell("-------------------------------", angsanaNewFont14, 0f, 1, Element.ALIGN_CENTER));
            signatureTable.addCell(createCell("-------------------------------", angsanaNewFont14, 0f, 1, Element.ALIGN_CENTER));
            signatureTable.addCell(createCell("-------------------------------", angsanaNewFont14, 0f, 1, Element.ALIGN_CENTER));
            signatureTable.addCell(createCell("Sales By", angsanaNewFont14, 0f, 1, Element.ALIGN_CENTER));
            signatureTable.addCell(createCell("Account By", angsanaNewFont14, 0f, 1, Element.ALIGN_CENTER));
            signatureTable.addCell(createCell("Approved By\n(For Customer)", angsanaNewFont14, 0f, 1, Element.ALIGN_CENTER));
            document.add(signatureTable);

        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            document.close();
        }
    }

    public static PdfPCell createCell(String content, Font font, float borderWidth, int colSpan, int alignment) {
        PdfPCell cell = new PdfPCell(new Paragraph(content, font));
        cell.setBorderWidth(borderWidth);
        cell.setColspan(colSpan);
        cell.setPaddingBottom(5);
        cell.setHorizontalAlignment(alignment);
        return cell;
    }

    public static PdfPCell createCellFixHeight(String content,Font font, float borderWidth, int colspan, int alignment, float height) {
        PdfPCell cell = new PdfPCell(new Paragraph(content, font));
        cell.setBorderWidth(borderWidth);
        cell.setColspan(colspan);
        cell.setPaddingBottom(5);
        cell.setPaddingLeft(5);
        cell.setFixedHeight(height);
        cell.setHorizontalAlignment(alignment);
        return cell;
    }


}
