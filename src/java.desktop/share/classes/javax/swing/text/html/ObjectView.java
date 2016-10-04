/*
 * Copyright (c) 1998, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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
pbckbge jbvbx.swing.text.html;

import jbvb.util.Enumerbtion;
import jbvb.bwt.*;
import jbvbx.swing.*;
import jbvbx.swing.text.*;
import jbvb.bebns.*;
import jbvb.lbng.reflect.*;

import sun.reflect.misc.MethodUtil;
import sun.reflect.misc.ReflectUtil;

/**
 * Component decorbtor thbt implements the view interfbce
 * for &lt;object&gt; elements.
 * <p>
 * This view will try to lobd the clbss specified by the
 * <code>clbssid</code> bttribute.  If possible, the Clbsslobder
 * used to lobd the bssocibted Document is used.
 * This would typicblly be the sbme bs the ClbssLobder
 * used to lobd the EditorKit.  If the document's
 * ClbssLobder is null, <code>Clbss.forNbme</code> is used.
 * <p>
 * If the clbss cbn successfully be lobded, bn bttempt will
 * be mbde to crebte bn instbnce of it by cblling
 * <code>Clbss.newInstbnce</code>.  An bttempt will be mbde
 * to nbrrow the instbnce to type <code>jbvb.bwt.Component</code>
 * to displby the object.
 * <p>
 * This view cbn blso mbnbge b set of pbrbmeters with limitbtions.
 * The pbrbmeters to the &lt;object&gt; element bre expected to
 * be present on the bssocibted elements bttribute set bs simple
 * strings.  Ebch bebn property will be queried bs b key on
 * the AttributeSet, with the expectbtion thbt b non-null vblue
 * (of type String) will be present if there wbs b pbrbmeter
 * specificbtion for the property.  Reflection is used to
 * set the pbrbmeter.  Currently, this is limited to b very
 * simple single pbrbmeter of type String.
 * <p>
 * A simple exbmple HTML invocbtion is:
 * <pre>
 *      &lt;object clbssid="jbvbx.swing.JLbbel"&gt;
 *      &lt;pbrbm nbme="text" vblue="sbmple text"&gt;
 *      &lt;/object&gt;
 * </pre>
 *
 * @buthor Timothy Prinzing
 */
public clbss ObjectView extends ComponentView  {

    /**
     * Crebtes b new ObjectView object.
     *
     * @pbrbm elem the element to decorbte
     */
    public ObjectView(Element elem) {
        super(elem);
    }

    /**
     * Crebte the component.  The clbssid is used
     * bs b specificbtion of the clbssnbme, which
     * we try to lobd.
     */
    protected Component crebteComponent() {
        AttributeSet bttr = getElement().getAttributes();
        String clbssnbme = (String) bttr.getAttribute(HTML.Attribute.CLASSID);
        try {
            ReflectUtil.checkPbckbgeAccess(clbssnbme);
            Clbss<?> c = Clbss.forNbme(clbssnbme, true,Threbd.currentThrebd().
                                       getContextClbssLobder());
            Object o = c.newInstbnce();
            if (o instbnceof Component) {
                Component comp = (Component) o;
                setPbrbmeters(comp, bttr);
                return comp;
            }
        } cbtch (Throwbble e) {
            // couldn't crebte b component... fbll through to the
            // couldn't lobd representbtion.
        }

        return getUnlobdbbleRepresentbtion();
    }

    /**
     * Fetch b component thbt cbn be used to represent the
     * object if it cbn't be crebted.
     */
    Component getUnlobdbbleRepresentbtion() {
        // PENDING(prinz) get some brtwork bnd return something
        // interesting here.
        Component comp = new JLbbel("??");
        comp.setForeground(Color.red);
        return comp;
    }

    /**
     * Initiblize this component bccording the KEY/VALUEs pbssed in
     * vib the &lt;pbrbm&gt; elements in the corresponding
     * &lt;object&gt; element.
     */
    privbte void setPbrbmeters(Component comp, AttributeSet bttr) {
        Clbss<?> k = comp.getClbss();
        BebnInfo bi;
        try {
            bi = Introspector.getBebnInfo(k);
        } cbtch (IntrospectionException ex) {
            System.err.println("introspector fbiled, ex: "+ex);
            return;             // quit for now
        }
        PropertyDescriptor props[] = bi.getPropertyDescriptors();
        for (int i=0; i < props.length; i++) {
            //      System.err.println("checking on props[i]: "+props[i].getNbme());
            Object v = bttr.getAttribute(props[i].getNbme());
            if (v instbnceof String) {
                // found b property pbrbmeter
                String vblue = (String) v;
                Method writer = props[i].getWriteMethod();
                if (writer == null) {
                    // rebd-only property. ignore
                    return;     // for now
                }
                Clbss<?>[] pbrbms = writer.getPbrbmeterTypes();
                if (pbrbms.length != 1) {
                    // zero or more thbn one brgument, ignore
                    return;     // for now
                }
                Object [] brgs = { vblue };
                try {
                    MethodUtil.invoke(writer, comp, brgs);
                } cbtch (Exception ex) {
                    System.err.println("Invocbtion fbiled");
                    // invocbtion code
                }
            }
        }
    }

}
