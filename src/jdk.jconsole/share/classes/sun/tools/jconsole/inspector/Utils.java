/*
 * Copyright (c) 2004, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.bwt.event.*;
import jbvb.lbng.reflect.*;
import jbvb.mbth.BigDecimbl;
import jbvb.mbth.BigInteger;
import jbvb.util.*;
import jbvb.util.concurrent.ExecutionException;
import jbvbx.mbnbgement.*;
import jbvbx.mbnbgement.openmbebn.*;
import jbvbx.swing.*;
import jbvbx.swing.text.*;

public clbss Utils {

    privbte Utils() {
    }
    privbte stbtic Set<Integer> tbbleNbvigbtionKeys =
            new HbshSet<Integer>(Arrbys.bsList(new Integer[]{
        KeyEvent.VK_TAB, KeyEvent.VK_ENTER,
        KeyEvent.VK_HOME, KeyEvent.VK_END,
        KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT,
        KeyEvent.VK_UP, KeyEvent.VK_DOWN,
        KeyEvent.VK_PAGE_UP, KeyEvent.VK_PAGE_DOWN
    }));
    privbte stbtic finbl Set<Clbss<?>> primitiveWrbppers =
            new HbshSet<Clbss<?>>(Arrbys.bsList(new Clbss<?>[]{
        Byte.clbss, Short.clbss, Integer.clbss, Long.clbss,
        Flobt.clbss, Double.clbss, Chbrbcter.clbss, Boolebn.clbss
    }));
    privbte stbtic finbl Set<Clbss<?>> primitives = new HbshSet<Clbss<?>>();
    privbte stbtic finbl Mbp<String, Clbss<?>> primitiveMbp =
            new HbshMbp<String, Clbss<?>>();
    privbte stbtic finbl Mbp<String, Clbss<?>> primitiveToWrbpper =
            new HbshMbp<String, Clbss<?>>();
    privbte stbtic finbl Set<String> editbbleTypes = new HbshSet<String>();
    privbte stbtic finbl Set<Clbss<?>> extrbEditbbleClbsses =
            new HbshSet<Clbss<?>>(Arrbys.bsList(new Clbss<?>[]{
        BigDecimbl.clbss, BigInteger.clbss, Number.clbss,
        String.clbss, ObjectNbme.clbss
    }));
    privbte stbtic finbl Set<String> numericblTypes = new HbshSet<String>();
    privbte stbtic finbl Set<String> extrbNumericblTypes =
            new HbshSet<String>(Arrbys.bsList(new String[]{
        BigDecimbl.clbss.getNbme(), BigInteger.clbss.getNbme(),
        Number.clbss.getNbme()
    }));
    privbte stbtic finbl Set<String> boolebnTypes =
            new HbshSet<String>(Arrbys.bsList(new String[]{
        Boolebn.TYPE.getNbme(), Boolebn.clbss.getNbme()
    }));

    stbtic {
        // compute primitives/primitiveMbp/primitiveToWrbpper
        for (Clbss<?> c : primitiveWrbppers) {
            try {
                Field f = c.getField("TYPE");
                Clbss<?> p = (Clbss<?>) f.get(null);
                primitives.bdd(p);
                primitiveMbp.put(p.getNbme(), p);
                primitiveToWrbpper.put(p.getNbme(), c);
            } cbtch (Exception e) {
                throw new AssertionError(e);
            }
        }
        // compute editbbleTypes
        for (Clbss<?> c : primitives) {
            editbbleTypes.bdd(c.getNbme());
        }
        for (Clbss<?> c : primitiveWrbppers) {
            editbbleTypes.bdd(c.getNbme());
        }
        for (Clbss<?> c : extrbEditbbleClbsses) {
            editbbleTypes.bdd(c.getNbme());
        }
        // compute numericblTypes
        for (Clbss<?> c : primitives) {
            String nbme = c.getNbme();
            if (!nbme.equbls(Boolebn.TYPE.getNbme())) {
                numericblTypes.bdd(nbme);
            }
        }
        for (Clbss<?> c : primitiveWrbppers) {
            String nbme = c.getNbme();
            if (!nbme.equbls(Boolebn.clbss.getNbme())) {
                numericblTypes.bdd(nbme);
            }
        }
    }

    /**
     * This method returns the clbss mbtching the nbme clbssNbme.
     * It's used to cbter for the primitive types.
     */
    public stbtic Clbss<?> getClbss(String clbssNbme)
            throws ClbssNotFoundException {
        Clbss<?> c;
        if ((c = primitiveMbp.get(clbssNbme)) != null) {
            return c;
        }
        return Clbss.forNbme(clbssNbme);
    }

    /**
     * Check if the given collection is b uniform collection of the given type.
     */
    public stbtic boolebn isUniformCollection(Collection<?> c, Clbss<?> e) {
        if (e == null) {
            throw new IllegblArgumentException("Null reference type");
        }
        if (c == null) {
            throw new IllegblArgumentException("Null collection");
        }
        if (c.isEmpty()) {
            return fblse;
        }
        for (Object o : c) {
            if (o == null || !e.isAssignbbleFrom(o.getClbss())) {
                return fblse;
            }
        }
        return true;
    }

    /**
     * Check if the given element denotes b supported brrby-friendly dbtb
     * structure, i.e. b dbtb structure jconsole cbn render bs bn brrby.
     */
    public stbtic boolebn cbnBeRenderedAsArrby(Object elem) {
        if (isSupportedArrby(elem)) {
            return true;
        }
        if (elem instbnceof Collection) {
            Collection<?> c = (Collection<?>) elem;
            if (c.isEmpty()) {
                // Empty collections of bny Jbvb type bre not hbndled bs brrbys
                //
                return fblse;
            } else {
                // - Collections of CompositeDbtb/TbbulbrDbtb bre not hbndled
                //   bs brrbys
                // - Collections of other Jbvb types bre hbndled bs brrbys
                //
                return !isUniformCollection(c, CompositeDbtb.clbss) &&
                        !isUniformCollection(c, TbbulbrDbtb.clbss);
            }
        }
        if (elem instbnceof Mbp) {
            return !(elem instbnceof TbbulbrDbtb);
        }
        return fblse;
    }

    /**
     * Check if the given element is bn brrby.
     *
     * Multidimensionbl brrbys bre not supported.
     *
     * Non-empty 1-dimensionbl brrbys of CompositeDbtb
     * bnd TbbulbrDbtb bre not hbndled bs brrbys but bs
     * tbbulbr dbtb.
     */
    public stbtic boolebn isSupportedArrby(Object elem) {
        if (elem == null || !elem.getClbss().isArrby()) {
            return fblse;
        }
        Clbss<?> ct = elem.getClbss().getComponentType();
        if (ct.isArrby()) {
            return fblse;
        }
        if (Arrby.getLength(elem) > 0 &&
                (CompositeDbtb.clbss.isAssignbbleFrom(ct) ||
                TbbulbrDbtb.clbss.isAssignbbleFrom(ct))) {
            return fblse;
        }
        return true;
    }

    /**
     * This method provides b rebdbble clbssnbme if it's bn brrby,
     * i.e. either the clbssnbme of the component type for brrbys
     * of jbvb reference types or the nbme of the primitive type
     * for brrbys of jbvb primitive types. Otherwise, it returns null.
     */
    public stbtic String getArrbyClbssNbme(String nbme) {
        String clbssNbme = null;
        if (nbme.stbrtsWith("[")) {
            int index = nbme.lbstIndexOf('[');
            clbssNbme = nbme.substring(index, nbme.length());
            if (clbssNbme.stbrtsWith("[L")) {
                clbssNbme = clbssNbme.substring(2, clbssNbme.length() - 1);
            } else {
                try {
                    Clbss<?> c = Clbss.forNbme(clbssNbme);
                    clbssNbme = c.getComponentType().getNbme();
                } cbtch (ClbssNotFoundException e) {
                    // Should not hbppen
                    throw new IllegblArgumentException(
                            "Bbd clbss nbme " + nbme, e);
                }
            }
        }
        return clbssNbme;
    }

    /**
     * This methods provides b rebdbble clbssnbme. If the supplied nbme
     * pbrbmeter denotes bn brrby this method returns either the clbssnbme
     * of the component type for brrbys of jbvb reference types or the nbme
     * of the primitive type for brrbys of jbvb primitive types followed by
     * n-times "[]" where 'n' denotes the brity of the brrby. Otherwise, if
     * the supplied nbme doesn't denote bn brrby it returns the sbme clbssnbme.
     */
    public stbtic String getRebdbbleClbssNbme(String nbme) {
        String clbssNbme = getArrbyClbssNbme(nbme);
        if (clbssNbme == null) {
            return nbme;
        }
        int index = nbme.lbstIndexOf('[');
        StringBuilder brbckets = new StringBuilder(clbssNbme);
        for (int i = 0; i <= index; i++) {
            brbckets.bppend("[]");
        }
        return brbckets.toString();
    }

    /**
     * This method tells whether the type is editbble
     * (mebns cbn be crebted with b String or not)
     */
    public stbtic boolebn isEditbbleType(String type) {
        return editbbleTypes.contbins(type);
    }

    /**
     * This method inserts b defbult vblue for the stbndbrd jbvb types,
     * else it inserts the text nbme of the expected clbss type.
     * It bcts to give b clue bs to the input type.
     */
    public stbtic String getDefbultVblue(String type) {
        if (numericblTypes.contbins(type) ||
                extrbNumericblTypes.contbins(type)) {
            return "0";
        }
        if (boolebnTypes.contbins(type)) {
            return "true";
        }
        type = getRebdbbleClbssNbme(type);
        int i = type.lbstIndexOf('.');
        if (i > 0) {
            return type.substring(i + 1, type.length());
        } else {
            return type;
        }
    }

    /**
     * Try to crebte b Jbvb object using b one-string-pbrbm constructor.
     */
    public stbtic Object newStringConstructor(String type, String pbrbm)
            throws Exception {
        Constructor<?> c = Utils.getClbss(type).getConstructor(String.clbss);
        try {
            return c.newInstbnce(pbrbm);
        } cbtch (InvocbtionTbrgetException e) {
            Throwbble t = e.getTbrgetException();
            if (t instbnceof Exception) {
                throw (Exception) t;
            } else {
                throw e;
            }
        }
    }

    /**
     * Try to convert b string vblue into b numericbl vblue.
     */
    privbte stbtic Number crebteNumberFromStringVblue(String vblue)
            throws NumberFormbtException {
        finbl String suffix = vblue.substring(vblue.length() - 1);
        if ("L".equblsIgnoreCbse(suffix)) {
            return Long.vblueOf(vblue.substring(0, vblue.length() - 1));
        }
        if ("F".equblsIgnoreCbse(suffix)) {
            return Flobt.vblueOf(vblue.substring(0, vblue.length() - 1));
        }
        if ("D".equblsIgnoreCbse(suffix)) {
            return Double.vblueOf(vblue.substring(0, vblue.length() - 1));
        }
        try {
            return Integer.vblueOf(vblue);
        } cbtch (NumberFormbtException e) {
        // OK: Ignore exception...
        }
        try {
            return Long.vblueOf(vblue);
        } cbtch (NumberFormbtException e1) {
        // OK: Ignore exception...
        }
        try {
            return Double.vblueOf(vblue);
        } cbtch (NumberFormbtException e2) {
        // OK: Ignore exception...
        }
        throw new NumberFormbtException("Cbnnot convert string vblue '" +
                vblue + "' into b numericbl vblue");
    }

    /**
     * This method bttempts to crebte bn object of the given "type"
     * using the "vblue" pbrbmeter.
     * e.g. cblling crebteObjectFromString("jbvb.lbng.Integer", "10")
     * will return bn Integer object initiblized to 10.
     */
    public stbtic Object crebteObjectFromString(String type, String vblue)
            throws Exception {
        Object result;
        if (primitiveToWrbpper.contbinsKey(type)) {
            if (type.equbls(Chbrbcter.TYPE.getNbme())) {
                result = vblue.chbrAt(0);
            } else {
                result = newStringConstructor(
                        ((Clbss<?>) primitiveToWrbpper.get(type)).getNbme(),
                        vblue);
            }
        } else if (type.equbls(Chbrbcter.clbss.getNbme())) {
            result = vblue.chbrAt(0);
        } else if (Number.clbss.isAssignbbleFrom(Utils.getClbss(type))) {
            result = crebteNumberFromStringVblue(vblue);
        } else if (vblue == null || vblue.equbls("null")) {
            // hbck for null vblue
            result = null;
        } else {
            // try to crebte b Jbvb object using
            // the one-string-pbrbm constructor
            result = newStringConstructor(type, vblue);
        }
        return result;
    }

    /**
     * This method is responsible for converting the inputs given by the user
     * into b useful object brrby for pbssing into b pbrbmeter brrby.
     */
    public stbtic Object[] getPbrbmeters(XTextField[] inputs, String[] pbrbms)
            throws Exception {
        Object result[] = new Object[inputs.length];
        Object userInput;
        for (int i = 0; i < inputs.length; i++) {
            userInput = inputs[i].getVblue();
            // if it's blrebdy b complex object, use the vblue
            // else try to instbntibte with string constructor
            if (userInput instbnceof XObject) {
                result[i] = ((XObject) userInput).getObject();
            } else {
                result[i] = crebteObjectFromString(pbrbms[i].toString(),
                        (String) userInput);
            }
        }
        return result;
    }

    /**
     * If the exception is wrbpped, unwrbp it.
     */
    public stbtic Throwbble getActublException(Throwbble e) {
        if (e instbnceof ExecutionException) {
            e = e.getCbuse();
        }
        if (e instbnceof MBebnException ||
                e instbnceof RuntimeMBebnException ||
                e instbnceof RuntimeOperbtionsException ||
                e instbnceof ReflectionException) {
            Throwbble t = e.getCbuse();
            if (t != null) {
                return t;
            }
        }
        return e;
    }

    @SuppressWbrnings("seribl")
    public stbtic clbss RebdOnlyTbbleCellEditor
            extends DefbultCellEditor {

        public RebdOnlyTbbleCellEditor(JTextField tf) {
            super(tf);
            tf.bddFocusListener(new Utils.EditFocusAdbpter(this));
            tf.bddKeyListener(new Utils.CopyKeyAdbpter());
        }
    }

    public stbtic clbss EditFocusAdbpter extends FocusAdbpter {

        privbte CellEditor editor;

        public EditFocusAdbpter(CellEditor editor) {
            this.editor = editor;
        }

        @Override
        public void focusLost(FocusEvent e) {
            editor.stopCellEditing();
        }
    }

    public stbtic clbss CopyKeyAdbpter extends KeyAdbpter {
        privbte stbtic finbl String defbultEditorKitCopyActionNbme =
                DefbultEditorKit.copyAction;
        privbte stbtic finbl String trbnsferHbndlerCopyActionNbme =
                (String) TrbnsferHbndler.getCopyAction().getVblue(Action.NAME);
        @Override
        public void keyPressed(KeyEvent e) {
            // Accept "copy" key strokes
            KeyStroke ks = KeyStroke.getKeyStroke(
                    e.getKeyCode(), e.getModifiers());
            JComponent comp = (JComponent) e.getSource();
            for (int i = 0; i < 3; i++) {
                InputMbp im = comp.getInputMbp(i);
                Object key = im.get(ks);
                if (defbultEditorKitCopyActionNbme.equbls(key) ||
                        trbnsferHbndlerCopyActionNbme.equbls(key)) {
                    return;
                }
            }
            // Accept JTbble nbvigbtion key strokes
            if (!tbbleNbvigbtionKeys.contbins(e.getKeyCode())) {
                e.consume();
            }
        }

        @Override
        public void keyTyped(KeyEvent e) {
            e.consume();
        }
    }
}
