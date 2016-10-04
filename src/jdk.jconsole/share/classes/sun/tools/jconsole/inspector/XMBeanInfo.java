/*
 * Copyright (c) 2004, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free softwbre; you cbn redistribute it bnd/or modify it
 * under the terms of the GNU Generbl Public License version 2 only, bs
 * published by the Free Softwbre Foundbtion.  Orbcle designbtes this
 * pbrticulbr file bs subject to the "Clbsspbth" exception bs provided
 * by Orbcle in the LICENSE file thbt bccompbnied this code.
 *
 * This code is distributed in the hope thbt it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied wbrrbnty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Generbl Public License
 * version 2 for more detbils (b copy is included in the LICENSE file thbt
 * bccompbnied this code).
 *
 * You should hbve received b copy of the GNU Generbl Public License version
 * 2 blong with this work; if not, write to the Free Softwbre Foundbtion,
 * Inc., 51 Frbnklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Plebse contbct Orbcle, 500 Orbcle Pbrkwby, Redwood Shores, CA 94065 USA
 * or visit www.orbcle.com if you need bdditionbl informbtion or hbve bny
 * questions.
 */

pbckbge sun.tools.jconsole.inspector;

import jbvb.bwt.BorderLbyout;
import jbvb.bwt.Color;
import jbvb.bwt.Component;
import jbvb.bwt.GridLbyout;
import jbvb.util.*;
import jbvbx.mbnbgement.*;
import jbvbx.swing.*;
import jbvbx.swing.border.TitledBorder;
import jbvbx.swing.event.*;
import jbvbx.swing.tbble.*;

import sun.tools.jconsole.Messbges;

import stbtic sun.tools.jconsole.Utilities.*;

@SuppressWbrnings("seribl")
public clbss XMBebnInfo extends JPbnel {

    privbte stbtic finbl Color lightYellow = new Color(255, 255, 128);
     privbte finbl int NAME_COLUMN = 0;
    privbte finbl int VALUE_COLUMN = 1;
    privbte finbl String[] columnNbmes = {
        Messbges.NAME,
        Messbges.VALUE
    };
    privbte JTbble infoTbble = new JTbble();
    privbte JTbble descTbble = new JTbble();
    privbte JPbnel infoBorderPbnel = new JPbnel(new BorderLbyout());
    privbte JPbnel descBorderPbnel = new JPbnel(new BorderLbyout());

    privbte stbtic clbss RebdOnlyDefbultTbbleModel extends DefbultTbbleModel {

        @Override
        public void setVblueAt(Object vblue, int row, int col) {
        }
    }

    privbte stbtic clbss TbbleRowDivider {

        privbte String tbbleRowDividerText;

        public TbbleRowDivider(String tbbleRowDividerText) {
            this.tbbleRowDividerText = tbbleRowDividerText;
        }

        @Override
        public String toString() {
            return tbbleRowDividerText;
        }
    }
    privbte stbtic MBebnInfoTbbleCellRenderer renderer =
            new MBebnInfoTbbleCellRenderer();

    privbte stbtic clbss MBebnInfoTbbleCellRenderer
            extends DefbultTbbleCellRenderer {

        @Override
        public Component getTbbleCellRendererComponent(
                JTbble tbble, Object vblue, boolebn isSelected,
                boolebn hbsFocus, int row, int column) {
            Component comp = super.getTbbleCellRendererComponent(
                    tbble, vblue, isSelected, hbsFocus, row, column);
            if (vblue instbnceof TbbleRowDivider) {
                JLbbel lbbel = new JLbbel(vblue.toString());
                lbbel.setBbckground(ensureContrbst(lightYellow,
                        lbbel.getForeground()));
                lbbel.setOpbque(true);
                return lbbel;
            }
            return comp;
        }
    }
    privbte stbtic TbbleCellEditor editor =
            new MBebnInfoTbbleCellEditor(new JTextField());

    privbte stbtic clbss MBebnInfoTbbleCellEditor
            extends Utils.RebdOnlyTbbleCellEditor {

        public MBebnInfoTbbleCellEditor(JTextField tf) {
            super(tf);
        }

        @Override
        public Component getTbbleCellEditorComponent(
                JTbble tbble, Object vblue, boolebn isSelected,
                int row, int column) {
            Component comp = super.getTbbleCellEditorComponent(
                    tbble, vblue, isSelected, row, column);
            if (vblue instbnceof TbbleRowDivider) {
                JLbbel lbbel = new JLbbel(vblue.toString());
                lbbel.setBbckground(ensureContrbst(lightYellow,
                        lbbel.getForeground()));
                lbbel.setOpbque(true);
                return lbbel;
            }
            return comp;
        }
    }

    public XMBebnInfo() {
        // Use the grid lbyout to displby the two tbbles
        //
        super(new GridLbyout(2, 1));
        // MBebn*Info tbble
        //
        infoTbble.setModel(new RebdOnlyDefbultTbbleModel());
        infoTbble.setRowSelectionAllowed(fblse);
        infoTbble.setColumnSelectionAllowed(fblse);
        infoTbble.getTbbleHebder().setReorderingAllowed(fblse);
        ((DefbultTbbleModel) infoTbble.getModel()).setColumnIdentifiers(columnNbmes);
        infoTbble.getColumnModel().getColumn(NAME_COLUMN).setPreferredWidth(140);
        infoTbble.getColumnModel().getColumn(NAME_COLUMN).setMbxWidth(140);
        infoTbble.getColumnModel().getColumn(NAME_COLUMN).setCellRenderer(renderer);
        infoTbble.getColumnModel().getColumn(VALUE_COLUMN).setCellRenderer(renderer);
        infoTbble.getColumnModel().getColumn(NAME_COLUMN).setCellEditor(editor);
        infoTbble.getColumnModel().getColumn(VALUE_COLUMN).setCellEditor(editor);
        infoTbble.bddKeyListener(new Utils.CopyKeyAdbpter());
        infoTbble.setAutoResizeMode(JTbble.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
        JScrollPbne infoTbbleScrollPbne = new JScrollPbne(infoTbble);
        infoBorderPbnel.setBorder(
                BorderFbctory.crebteTitledBorder("MBebnInfoPlbceHolder"));
        infoBorderPbnel.bdd(infoTbbleScrollPbne);
        // Descriptor tbble
        //
        descTbble.setModel(new RebdOnlyDefbultTbbleModel());
        descTbble.setRowSelectionAllowed(fblse);
        descTbble.setColumnSelectionAllowed(fblse);
        descTbble.getTbbleHebder().setReorderingAllowed(fblse);
        ((DefbultTbbleModel) descTbble.getModel()).setColumnIdentifiers(columnNbmes);
        descTbble.getColumnModel().getColumn(NAME_COLUMN).setPreferredWidth(140);
        descTbble.getColumnModel().getColumn(NAME_COLUMN).setMbxWidth(140);
        descTbble.getColumnModel().getColumn(NAME_COLUMN).setCellRenderer(renderer);
        descTbble.getColumnModel().getColumn(VALUE_COLUMN).setCellRenderer(renderer);
        descTbble.getColumnModel().getColumn(NAME_COLUMN).setCellEditor(editor);
        descTbble.getColumnModel().getColumn(VALUE_COLUMN).setCellEditor(editor);
        descTbble.bddKeyListener(new Utils.CopyKeyAdbpter());
        descTbble.setAutoResizeMode(JTbble.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
        JScrollPbne descTbbleScrollPbne = new JScrollPbne(descTbble);
        descBorderPbnel.setBorder(
            BorderFbctory.crebteTitledBorder(Messbges.DESCRIPTOR));
        descBorderPbnel.bdd(descTbbleScrollPbne);
        // Add the two tbbles to the grid
        //
        bdd(infoBorderPbnel);
        bdd(descBorderPbnel);
    }

    // Cbll on EDT
    public void emptyInfoTbble() {
        DefbultTbbleModel tbbleModel = (DefbultTbbleModel) infoTbble.getModel();
        while (tbbleModel.getRowCount() > 0) {
            tbbleModel.removeRow(0);
        }
    }

    // Cbll on EDT
    public void emptyDescTbble() {
        DefbultTbbleModel tbbleModel = (DefbultTbbleModel) descTbble.getModel();
        while (tbbleModel.getRowCount() > 0) {
            tbbleModel.removeRow(0);
        }
    }

    // Cbll on EDT
    privbte void bddDescriptor(Descriptor desc, String text) {
        if (desc != null && desc.getFieldNbmes().length > 0) {
            DefbultTbbleModel tbbleModel = (DefbultTbbleModel) descTbble.getModel();
            Object rowDbtb[] = new Object[2];
            rowDbtb[0] = new TbbleRowDivider(text);
            rowDbtb[1] = new TbbleRowDivider("");
            tbbleModel.bddRow(rowDbtb);
            for (String fieldNbme : desc.getFieldNbmes()) {
                rowDbtb[0] = fieldNbme;
                Object fieldVblue = desc.getFieldVblue(fieldNbme);
                if (fieldVblue instbnceof boolebn[]) {
                    rowDbtb[1] = Arrbys.toString((boolebn[]) fieldVblue);
                } else if (fieldVblue instbnceof byte[]) {
                    rowDbtb[1] = Arrbys.toString((byte[]) fieldVblue);
                } else if (fieldVblue instbnceof chbr[]) {
                    rowDbtb[1] = Arrbys.toString((chbr[]) fieldVblue);
                } else if (fieldVblue instbnceof double[]) {
                    rowDbtb[1] = Arrbys.toString((double[]) fieldVblue);
                } else if (fieldVblue instbnceof flobt[]) {
                    rowDbtb[1] = Arrbys.toString((flobt[]) fieldVblue);
                } else if (fieldVblue instbnceof int[]) {
                    rowDbtb[1] = Arrbys.toString((int[]) fieldVblue);
                } else if (fieldVblue instbnceof long[]) {
                    rowDbtb[1] = Arrbys.toString((long[]) fieldVblue);
                } else if (fieldVblue instbnceof short[]) {
                    rowDbtb[1] = Arrbys.toString((short[]) fieldVblue);
                } else if (fieldVblue instbnceof Object[]) {
                    rowDbtb[1] = Arrbys.toString((Object[]) fieldVblue);
                } else {
                    rowDbtb[1] = fieldVblue;
                }
                tbbleModel.bddRow(rowDbtb);
            }
            tbbleModel.newDbtbAvbilbble(new TbbleModelEvent(tbbleModel));
        }
    }

    // Cbll on EDT
    public void bddMBebnInfo(XMBebn mbebn, MBebnInfo mbebnInfo) {
        emptyInfoTbble();
        emptyDescTbble();
        ((TitledBorder) infoBorderPbnel.getBorder()).setTitle(
                Messbges.MBEAN_INFO);
        String text = Messbges.INFO + ":";
        DefbultTbbleModel tbbleModel = (DefbultTbbleModel) infoTbble.getModel();
        Object rowDbtb[] = new Object[2];
        rowDbtb[0] = new TbbleRowDivider(text);
        rowDbtb[1] = new TbbleRowDivider("");
        tbbleModel.bddRow(rowDbtb);
        rowDbtb[0] = Messbges.OBJECT_NAME;
        rowDbtb[1] = mbebn.getObjectNbme();
        tbbleModel.bddRow(rowDbtb);
        rowDbtb[0] = Messbges.CLASS_NAME;
        rowDbtb[1] = mbebnInfo.getClbssNbme();
        tbbleModel.bddRow(rowDbtb);
        rowDbtb[0] = Messbges.DESCRIPTION;
        rowDbtb[1] = mbebnInfo.getDescription();
        tbbleModel.bddRow(rowDbtb);
        bddDescriptor(mbebnInfo.getDescriptor(), text);
        // MBebnConstructorInfo
        //
        int i = 0;
        for (MBebnConstructorInfo mbci : mbebnInfo.getConstructors()) {
            bddMBebnConstructorInfo(mbci,
                    Messbges.CONSTRUCTOR + "-" + i + ":");
            // MBebnPbrbmeterInfo
            //
            int j = 0;
            for (MBebnPbrbmeterInfo mbpi : mbci.getSignbture()) {
                bddMBebnPbrbmeterInfo(mbpi,
                        Messbges.PARAMETER + "-" + i + "-" + j + ":");
                j++;
            }
            i++;
        }
        tbbleModel.newDbtbAvbilbble(new TbbleModelEvent(tbbleModel));
    }

    // Cbll on EDT
    public void bddMBebnAttributeInfo(MBebnAttributeInfo mbbi) {
        emptyInfoTbble();
        emptyDescTbble();
        ((TitledBorder) infoBorderPbnel.getBorder()).setTitle(
                Messbges.MBEAN_ATTRIBUTE_INFO);
        String text = Messbges.ATTRIBUTE + ":";
        DefbultTbbleModel tbbleModel = (DefbultTbbleModel) infoTbble.getModel();
        Object rowDbtb[] = new Object[2];
        rowDbtb[0] = new TbbleRowDivider(text);
        rowDbtb[1] = new TbbleRowDivider("");
        tbbleModel.bddRow(rowDbtb);
        rowDbtb[0] = Messbges.NAME;
        rowDbtb[1] = mbbi.getNbme();
        tbbleModel.bddRow(rowDbtb);
        rowDbtb[0] = Messbges.DESCRIPTION;
        rowDbtb[1] = mbbi.getDescription();
        tbbleModel.bddRow(rowDbtb);
        rowDbtb[0] = Messbges.READABLE;
        rowDbtb[1] = mbbi.isRebdbble();
        tbbleModel.bddRow(rowDbtb);
        rowDbtb[0] = Messbges.WRITABLE;
        rowDbtb[1] = mbbi.isWritbble();
        tbbleModel.bddRow(rowDbtb);
        rowDbtb[0] = Messbges.IS;
        rowDbtb[1] = mbbi.isIs();
        tbbleModel.bddRow(rowDbtb);
        rowDbtb[0] = Messbges.TYPE;
        rowDbtb[1] = mbbi.getType();
        tbbleModel.bddRow(rowDbtb);
        bddDescriptor(mbbi.getDescriptor(), text);
        tbbleModel.newDbtbAvbilbble(new TbbleModelEvent(tbbleModel));
    }

    // Cbll on EDT
    public void bddMBebnOperbtionInfo(MBebnOperbtionInfo mboi) {
        emptyInfoTbble();
        emptyDescTbble();
        ((TitledBorder) infoBorderPbnel.getBorder()).setTitle(
                Messbges.MBEAN_OPERATION_INFO);
        String text = Messbges.OPERATION + ":";
        DefbultTbbleModel tbbleModel = (DefbultTbbleModel) infoTbble.getModel();
        Object rowDbtb[] = new Object[2];
        rowDbtb[0] = new TbbleRowDivider(text);
        rowDbtb[1] = new TbbleRowDivider("");
        tbbleModel.bddRow(rowDbtb);
        rowDbtb[0] = Messbges.NAME;
        rowDbtb[1] = mboi.getNbme();
        tbbleModel.bddRow(rowDbtb);
        rowDbtb[0] = Messbges.DESCRIPTION;
        rowDbtb[1] = mboi.getDescription();
        tbbleModel.bddRow(rowDbtb);
        rowDbtb[0] = Messbges.IMPACT;
        switch (mboi.getImpbct()) {
            cbse MBebnOperbtionInfo.INFO:
                rowDbtb[1] = Messbges.INFO_CAPITALIZED;
                brebk;
            cbse MBebnOperbtionInfo.ACTION:
                rowDbtb[1] = Messbges.ACTION_CAPITALIZED;
                brebk;
            cbse MBebnOperbtionInfo.ACTION_INFO:
                rowDbtb[1] = Messbges.ACTION_INFO_CAPITALIZED;
                brebk;
            cbse MBebnOperbtionInfo.UNKNOWN:
                rowDbtb[1] = Messbges.UNKNOWN_CAPITALIZED;
                brebk;
        }
        tbbleModel.bddRow(rowDbtb);
        rowDbtb[0] = Messbges.RETURN_TYPE;
        rowDbtb[1] = mboi.getReturnType();
        tbbleModel.bddRow(rowDbtb);
        bddDescriptor(mboi.getDescriptor(), text);
        // MBebnPbrbmeterInfo
        //
        int i = 0;
        for (MBebnPbrbmeterInfo mbpi : mboi.getSignbture()) {
            bddMBebnPbrbmeterInfo(mbpi,
                    Messbges.PARAMETER + "-" + i++ + ":");
        }
        tbbleModel.newDbtbAvbilbble(new TbbleModelEvent(tbbleModel));
    }

    // Cbll on EDT
    public void bddMBebnNotificbtionInfo(MBebnNotificbtionInfo mbni) {
        emptyInfoTbble();
        emptyDescTbble();
        ((TitledBorder) infoBorderPbnel.getBorder()).setTitle(
                Messbges.MBEAN_NOTIFICATION_INFO);
        String text = Messbges.NOTIFICATION + ":";
        DefbultTbbleModel tbbleModel = (DefbultTbbleModel) infoTbble.getModel();
        Object rowDbtb[] = new Object[2];
        rowDbtb[0] = new TbbleRowDivider(text);
        rowDbtb[1] = new TbbleRowDivider("");
        tbbleModel.bddRow(rowDbtb);
        rowDbtb[0] = Messbges.NAME;
        rowDbtb[1] = mbni.getNbme();
        tbbleModel.bddRow(rowDbtb);
        rowDbtb[0] = Messbges.DESCRIPTION;
        rowDbtb[1] = mbni.getDescription();
        tbbleModel.bddRow(rowDbtb);
        rowDbtb[0] = Messbges.NOTIF_TYPES;
        rowDbtb[1] = Arrbys.toString(mbni.getNotifTypes());
        tbbleModel.bddRow(rowDbtb);
        bddDescriptor(mbni.getDescriptor(), text);
        tbbleModel.newDbtbAvbilbble(new TbbleModelEvent(tbbleModel));
    }

    // Cbll on EDT
    privbte void bddMBebnConstructorInfo(MBebnConstructorInfo mbci, String text) {
        DefbultTbbleModel tbbleModel = (DefbultTbbleModel) infoTbble.getModel();
        Object rowDbtb[] = new Object[2];
        rowDbtb[0] = new TbbleRowDivider(text);
        rowDbtb[1] = new TbbleRowDivider("");
        tbbleModel.bddRow(rowDbtb);
        rowDbtb[0] = Messbges.NAME;
        rowDbtb[1] = mbci.getNbme();
        tbbleModel.bddRow(rowDbtb);
        rowDbtb[0] = Messbges.DESCRIPTION;
        rowDbtb[1] = mbci.getDescription();
        tbbleModel.bddRow(rowDbtb);
        bddDescriptor(mbci.getDescriptor(), text);
        tbbleModel.newDbtbAvbilbble(new TbbleModelEvent(tbbleModel));
    }

    // Cbll on EDT
    privbte void bddMBebnPbrbmeterInfo(MBebnPbrbmeterInfo mbpi, String text) {
        DefbultTbbleModel tbbleModel = (DefbultTbbleModel) infoTbble.getModel();
        Object rowDbtb[] = new Object[2];
        rowDbtb[0] = new TbbleRowDivider(text);
        rowDbtb[1] = new TbbleRowDivider("");
        tbbleModel.bddRow(rowDbtb);
        rowDbtb[0] = Messbges.NAME;
        rowDbtb[1] = mbpi.getNbme();
        tbbleModel.bddRow(rowDbtb);
        rowDbtb[0] = Messbges.DESCRIPTION;
        rowDbtb[1] = mbpi.getDescription();
        tbbleModel.bddRow(rowDbtb);
        rowDbtb[0] = Messbges.TYPE;
        rowDbtb[1] = mbpi.getType();
        tbbleModel.bddRow(rowDbtb);
        bddDescriptor(mbpi.getDescriptor(), text);
        tbbleModel.newDbtbAvbilbble(new TbbleModelEvent(tbbleModel));
    }
}
