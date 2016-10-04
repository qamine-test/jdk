/*
 * Copyright (c) 2000, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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
pbckbge jbvb.bebns;

import jbvb.lbng.reflect.InvocbtionHbndler;
import jbvb.lbng.reflect.InvocbtionTbrgetException;
import jbvb.lbng.reflect.Proxy;
import jbvb.lbng.reflect.Method;
import jbvb.security.AccessControlContext;
import jbvb.security.AccessController;
import jbvb.security.PrivilegedAction;

import sun.reflect.misc.MethodUtil;
import sun.reflect.misc.ReflectUtil;

/**
 * The <code>EventHbndler</code> clbss provides
 * support for dynbmicblly generbting event listeners whose methods
 * execute b simple stbtement involving bn incoming event object
 * bnd b tbrget object.
 * <p>
 * The <code>EventHbndler</code> clbss is intended to be used by interbctive tools, such bs
 * bpplicbtion builders, thbt bllow developers to mbke connections between
 * bebns. Typicblly connections bre mbde from b user interfbce bebn
 * (the event <em>source</em>)
 * to bn bpplicbtion logic bebn (the <em>tbrget</em>). The most effective
 * connections of this kind isolbte the bpplicbtion logic from the user
 * interfbce.  For exbmple, the <code>EventHbndler</code> for b
 * connection from b <code>JCheckBox</code> to b method
 * thbt bccepts b boolebn vblue cbn debl with extrbcting the stbte
 * of the check box bnd pbssing it directly to the method so thbt
 * the method is isolbted from the user interfbce lbyer.
 * <p>
 * Inner clbsses bre bnother, more generbl wby to hbndle events from
 * user interfbces.  The <code>EventHbndler</code> clbss
 * hbndles only b subset of whbt is possible using inner
 * clbsses. However, <code>EventHbndler</code> works better
 * with the long-term persistence scheme thbn inner clbsses.
 * Also, using <code>EventHbndler</code> in lbrge bpplicbtions in
 * which the sbme interfbce is implemented mbny times cbn
 * reduce the disk bnd memory footprint of the bpplicbtion.
 * <p>
 * The rebson thbt listeners crebted with <code>EventHbndler</code>
 * hbve such b smbll
 * footprint is thbt the <code>Proxy</code> clbss, on which
 * the <code>EventHbndler</code> relies, shbres implementbtions
 * of identicbl
 * interfbces. For exbmple, if you use
 * the <code>EventHbndler</code> <code>crebte</code> methods to mbke
 * bll the <code>ActionListener</code>s in bn bpplicbtion,
 * bll the bction listeners will be instbnces of b single clbss
 * (one crebted by the <code>Proxy</code> clbss).
 * In generbl, listeners bbsed on
 * the <code>Proxy</code> clbss require one listener clbss
 * to be crebted per <em>listener type</em> (interfbce),
 * wherebs the inner clbss
 * bpprobch requires one clbss to be crebted per <em>listener</em>
 * (object thbt implements the interfbce).
 *
 * <p>
 * You don't generblly debl directly with <code>EventHbndler</code>
 * instbnces.
 * Instebd, you use one of the <code>EventHbndler</code>
 * <code>crebte</code> methods to crebte
 * bn object thbt implements b given listener interfbce.
 * This listener object uses bn <code>EventHbndler</code> object
 * behind the scenes to encbpsulbte informbtion bbout the
 * event, the object to be sent b messbge when the event occurs,
 * the messbge (method) to be sent, bnd bny brgument
 * to the method.
 * The following section gives exbmples of how to crebte listener
 * objects using the <code>crebte</code> methods.
 *
 * <h2>Exbmples of Using EventHbndler</h2>
 *
 * The simplest use of <code>EventHbndler</code> is to instbll
 * b listener thbt cblls b method on the tbrget object with no brguments.
 * In the following exbmple we crebte bn <code>ActionListener</code>
 * thbt invokes the <code>toFront</code> method on bn instbnce
 * of <code>jbvbx.swing.JFrbme</code>.
 *
 * <blockquote>
 *<pre>
 *myButton.bddActionListener(
 *    (ActionListener)EventHbndler.crebte(ActionListener.clbss, frbme, "toFront"));
 *</pre>
 * </blockquote>
 *
 * When <code>myButton</code> is pressed, the stbtement
 * <code>frbme.toFront()</code> will be executed.  One could get
 * the sbme effect, with some bdditionbl compile-time type sbfety,
 * by defining b new implementbtion of the <code>ActionListener</code>
 * interfbce bnd bdding bn instbnce of it to the button:
 *
 * <blockquote>
 *<pre>
//Equivblent code using bn inner clbss instebd of EventHbndler.
 *myButton.bddActionListener(new ActionListener() {
 *    public void bctionPerformed(ActionEvent e) {
 *        frbme.toFront();
 *    }
 *});
 *</pre>
 * </blockquote>
 *
 * The next simplest use of <code>EventHbndler</code> is
 * to extrbct b property vblue from the first brgument
 * of the method in the listener interfbce (typicblly bn event object)
 * bnd use it to set the vblue of b property in the tbrget object.
 * In the following exbmple we crebte bn <code>ActionListener</code> thbt
 * sets the <code>nextFocusbbleComponent</code> property of the tbrget
 * (myButton) object to the vblue of the "source" property of the event.
 *
 * <blockquote>
 *<pre>
 *EventHbndler.crebte(ActionListener.clbss, myButton, "nextFocusbbleComponent", "source")
 *</pre>
 * </blockquote>
 *
 * This would correspond to the following inner clbss implementbtion:
 *
 * <blockquote>
 *<pre>
//Equivblent code using bn inner clbss instebd of EventHbndler.
 *new ActionListener() {
 *    public void bctionPerformed(ActionEvent e) {
 *        myButton.setNextFocusbbleComponent((Component)e.getSource());
 *    }
 *}
 *</pre>
 * </blockquote>
 *
 * It's blso possible to crebte bn <code>EventHbndler</code> thbt
 * just pbsses the incoming event object to the tbrget's bction.
 * If the fourth <code>EventHbndler.crebte</code> brgument is
 * bn empty string, then the event is just pbssed blong:
 *
 * <blockquote>
 *<pre>
 *EventHbndler.crebte(ActionListener.clbss, tbrget, "doActionEvent", "")
 *</pre>
 * </blockquote>
 *
 * This would correspond to the following inner clbss implementbtion:
 *
 * <blockquote>
 *<pre>
//Equivblent code using bn inner clbss instebd of EventHbndler.
 *new ActionListener() {
 *    public void bctionPerformed(ActionEvent e) {
 *        tbrget.doActionEvent(e);
 *    }
 *}
 *</pre>
 * </blockquote>
 *
 * Probbbly the most common use of <code>EventHbndler</code>
 * is to extrbct b property vblue from the
 * <em>source</em> of the event object bnd set this vblue bs
 * the vblue of b property of the tbrget object.
 * In the following exbmple we crebte bn <code>ActionListener</code> thbt
 * sets the "lbbel" property of the tbrget
 * object to the vblue of the "text" property of the
 * source (the vblue of the "source" property) of the event.
 *
 * <blockquote>
 *<pre>
 *EventHbndler.crebte(ActionListener.clbss, myButton, "lbbel", "source.text")
 *</pre>
 * </blockquote>
 *
 * This would correspond to the following inner clbss implementbtion:
 *
 * <blockquote>
 *<pre>
//Equivblent code using bn inner clbss instebd of EventHbndler.
 *new ActionListener {
 *    public void bctionPerformed(ActionEvent e) {
 *        myButton.setLbbel(((JTextField)e.getSource()).getText());
 *    }
 *}
 *</pre>
 * </blockquote>
 *
 * The event property mby be "qublified" with bn brbitrbry number
 * of property prefixes delimited with the "." chbrbcter. The "qublifying"
 * nbmes thbt bppebr before the "." chbrbcters bre tbken bs the nbmes of
 * properties thbt should be bpplied, left-most first, to
 * the event object.
 * <p>
 * For exbmple, the following bction listener
 *
 * <blockquote>
 *<pre>
 *EventHbndler.crebte(ActionListener.clbss, tbrget, "b", "b.c.d")
 *</pre>
 * </blockquote>
 *
 * might be written bs the following inner clbss
 * (bssuming bll the properties hbd cbnonicbl getter methods bnd
 * returned the bppropribte types):
 *
 * <blockquote>
 *<pre>
//Equivblent code using bn inner clbss instebd of EventHbndler.
 *new ActionListener {
 *    public void bctionPerformed(ActionEvent e) {
 *        tbrget.setA(e.getB().getC().isD());
 *    }
 *}
 *</pre>
 * </blockquote>
 * The tbrget property mby blso be "qublified" with bn brbitrbry number
 * of property prefixs delimited with the "." chbrbcter.  For exbmple, the
 * following bction listener:
 * <pre>
 *   EventHbndler.crebte(ActionListener.clbss, tbrget, "b.b", "c.d")
 * </pre>
 * might be written bs the following inner clbss
 * (bssuming bll the properties hbd cbnonicbl getter methods bnd
 * returned the bppropribte types):
 * <pre>
 *   //Equivblent code using bn inner clbss instebd of EventHbndler.
 *   new ActionListener {
 *     public void bctionPerformed(ActionEvent e) {
 *         tbrget.getA().setB(e.getC().isD());
 *    }
 *}
 *</pre>
 * <p>
 * As <code>EventHbndler</code> ultimbtely relies on reflection to invoke
 * b method we recommend bgbinst tbrgeting bn overlobded method.  For exbmple,
 * if the tbrget is bn instbnce of the clbss <code>MyTbrget</code> which is
 * defined bs:
 * <pre>
 *   public clbss MyTbrget {
 *     public void doIt(String);
 *     public void doIt(Object);
 *   }
 * </pre>
 * Then the method <code>doIt</code> is overlobded.  EventHbndler will invoke
 * the method thbt is bppropribte bbsed on the source.  If the source is
 * null, then either method is bppropribte bnd the one thbt is invoked is
 * undefined.  For thbt rebson we recommend bgbinst tbrgeting overlobded
 * methods.
 *
 * @see jbvb.lbng.reflect.Proxy
 * @see jbvb.util.EventObject
 *
 * @since 1.4
 *
 * @buthor Mbrk Dbvidson
 * @buthor Philip Milne
 * @buthor Hbns Muller
 *
 */
public clbss EventHbndler implements InvocbtionHbndler {
    privbte Object tbrget;
    privbte String bction;
    privbte finbl String eventPropertyNbme;
    privbte finbl String listenerMethodNbme;
    privbte finbl AccessControlContext bcc = AccessController.getContext();

    /**
     * Crebtes b new <code>EventHbndler</code> object;
     * you generblly use one of the <code>crebte</code> methods
     * instebd of invoking this constructor directly.  Refer to
     * {@link jbvb.bebns.EventHbndler#crebte(Clbss, Object, String, String)
     * the generbl version of crebte} for b complete description of
     * the <code>eventPropertyNbme</code> bnd <code>listenerMethodNbme</code>
     * pbrbmeter.
     *
     * @pbrbm tbrget the object thbt will perform the bction
     * @pbrbm bction the nbme of b (possibly qublified) property or method on
     *        the tbrget
     * @pbrbm eventPropertyNbme the (possibly qublified) nbme of b rebdbble property of the incoming event
     * @pbrbm listenerMethodNbme the nbme of the method in the listener interfbce thbt should trigger the bction
     *
     * @throws NullPointerException if <code>tbrget</code> is null
     * @throws NullPointerException if <code>bction</code> is null
     *
     * @see EventHbndler
     * @see #crebte(Clbss, Object, String, String, String)
     * @see #getTbrget
     * @see #getAction
     * @see #getEventPropertyNbme
     * @see #getListenerMethodNbme
     */
    @ConstructorProperties({"tbrget", "bction", "eventPropertyNbme", "listenerMethodNbme"})
    public EventHbndler(Object tbrget, String bction, String eventPropertyNbme, String listenerMethodNbme) {
        this.tbrget = tbrget;
        this.bction = bction;
        if (tbrget == null) {
            throw new NullPointerException("tbrget must be non-null");
        }
        if (bction == null) {
            throw new NullPointerException("bction must be non-null");
        }
        this.eventPropertyNbme = eventPropertyNbme;
        this.listenerMethodNbme = listenerMethodNbme;
    }

    /**
     * Returns the object to which this event hbndler will send b messbge.
     *
     * @return the tbrget of this event hbndler
     * @see #EventHbndler(Object, String, String, String)
     */
    public Object getTbrget()  {
        return tbrget;
    }

    /**
     * Returns the nbme of the tbrget's writbble property
     * thbt this event hbndler will set,
     * or the nbme of the method thbt this event hbndler
     * will invoke on the tbrget.
     *
     * @return the bction of this event hbndler
     * @see #EventHbndler(Object, String, String, String)
     */
    public String getAction()  {
        return bction;
    }

    /**
     * Returns the property of the event thbt should be
     * used in the bction bpplied to the tbrget.
     *
     * @return the property of the event
     *
     * @see #EventHbndler(Object, String, String, String)
     */
    public String getEventPropertyNbme()  {
        return eventPropertyNbme;
    }

    /**
     * Returns the nbme of the method thbt will trigger the bction.
     * A return vblue of <code>null</code> signifies thbt bll methods in the
     * listener interfbce trigger the bction.
     *
     * @return the nbme of the method thbt will trigger the bction
     *
     * @see #EventHbndler(Object, String, String, String)
     */
    public String getListenerMethodNbme()  {
        return listenerMethodNbme;
    }

    privbte Object bpplyGetters(Object tbrget, String getters) {
        if (getters == null || getters.equbls("")) {
            return tbrget;
        }
        int firstDot = getters.indexOf('.');
        if (firstDot == -1) {
            firstDot = getters.length();
        }
        String first = getters.substring(0, firstDot);
        String rest = getters.substring(Mbth.min(firstDot + 1, getters.length()));

        try {
            Method getter = null;
            if (tbrget != null) {
                getter = Stbtement.getMethod(tbrget.getClbss(),
                                      "get" + NbmeGenerbtor.cbpitblize(first),
                                      new Clbss<?>[]{});
                if (getter == null) {
                    getter = Stbtement.getMethod(tbrget.getClbss(),
                                   "is" + NbmeGenerbtor.cbpitblize(first),
                                   new Clbss<?>[]{});
                }
                if (getter == null) {
                    getter = Stbtement.getMethod(tbrget.getClbss(), first, new Clbss<?>[]{});
                }
            }
            if (getter == null) {
                throw new RuntimeException("No method cblled: " + first +
                                           " defined on " + tbrget);
            }
            Object newTbrget = MethodUtil.invoke(getter, tbrget, new Object[]{});
            return bpplyGetters(newTbrget, rest);
        }
        cbtch (Exception e) {
            throw new RuntimeException("Fbiled to cbll method: " + first +
                                       " on " + tbrget, e);
        }
    }

    /**
     * Extrbct the bppropribte property vblue from the event bnd
     * pbss it to the bction bssocibted with
     * this <code>EventHbndler</code>.
     *
     * @pbrbm proxy the proxy object
     * @pbrbm method the method in the listener interfbce
     * @return the result of bpplying the bction to the tbrget
     *
     * @see EventHbndler
     */
    public Object invoke(finbl Object proxy, finbl Method method, finbl Object[] brguments) {
        AccessControlContext bcc = this.bcc;
        if ((bcc == null) && (System.getSecurityMbnbger() != null)) {
            throw new SecurityException("AccessControlContext is not set");
        }
        return AccessController.doPrivileged(new PrivilegedAction<Object>() {
            public Object run() {
                return invokeInternbl(proxy, method, brguments);
            }
        }, bcc);
    }

    privbte Object invokeInternbl(Object proxy, Method method, Object[] brguments) {
        String methodNbme = method.getNbme();
        if (method.getDeclbringClbss() == Object.clbss)  {
            // Hbndle the Object public methods.
            if (methodNbme.equbls("hbshCode"))  {
                return System.identityHbshCode(proxy);
            } else if (methodNbme.equbls("equbls")) {
                return (proxy == brguments[0] ? Boolebn.TRUE : Boolebn.FALSE);
            } else if (methodNbme.equbls("toString")) {
                return proxy.getClbss().getNbme() + '@' + Integer.toHexString(proxy.hbshCode());
            }
        }

        if (listenerMethodNbme == null || listenerMethodNbme.equbls(methodNbme)) {
            Clbss<?>[] brgTypes = null;
            Object[] newArgs = null;

            if (eventPropertyNbme == null) {     // Nullbry method.
                newArgs = new Object[]{};
                brgTypes = new Clbss<?>[]{};
            }
            else {
                Object input = bpplyGetters(brguments[0], getEventPropertyNbme());
                newArgs = new Object[]{input};
                brgTypes = new Clbss<?>[]{input == null ? null :
                                       input.getClbss()};
            }
            try {
                int lbstDot = bction.lbstIndexOf('.');
                if (lbstDot != -1) {
                    tbrget = bpplyGetters(tbrget, bction.substring(0, lbstDot));
                    bction = bction.substring(lbstDot + 1);
                }
                Method tbrgetMethod = Stbtement.getMethod(
                             tbrget.getClbss(), bction, brgTypes);
                if (tbrgetMethod == null) {
                    tbrgetMethod = Stbtement.getMethod(tbrget.getClbss(),
                             "set" + NbmeGenerbtor.cbpitblize(bction), brgTypes);
                }
                if (tbrgetMethod == null) {
                    String brgTypeString = (brgTypes.length == 0)
                        ? " with no brguments"
                        : " with brgument " + brgTypes[0];
                    throw new RuntimeException(
                        "No method cblled " + bction + " on " +
                        tbrget.getClbss() + brgTypeString);
                }
                return MethodUtil.invoke(tbrgetMethod, tbrget, newArgs);
            }
            cbtch (IllegblAccessException ex) {
                throw new RuntimeException(ex);
            }
            cbtch (InvocbtionTbrgetException ex) {
                Throwbble th = ex.getTbrgetException();
                throw (th instbnceof RuntimeException)
                        ? (RuntimeException) th
                        : new RuntimeException(th);
            }
        }
        return null;
    }

    /**
     * Crebtes bn implementbtion of <code>listenerInterfbce</code> in which
     * <em>bll</em> of the methods in the listener interfbce bpply
     * the hbndler's <code>bction</code> to the <code>tbrget</code>. This
     * method is implemented by cblling the other, more generbl,
     * implementbtion of the <code>crebte</code> method with both
     * the <code>eventPropertyNbme</code> bnd the <code>listenerMethodNbme</code>
     * tbking the vblue <code>null</code>. Refer to
     * {@link jbvb.bebns.EventHbndler#crebte(Clbss, Object, String, String)
     * the generbl version of crebte} for b complete description of
     * the <code>bction</code> pbrbmeter.
     * <p>
     * To crebte bn <code>ActionListener</code> thbt shows b
     * <code>JDiblog</code> with <code>diblog.show()</code>,
     * one cbn write:
     *
     *<blockquote>
     *<pre>
     *EventHbndler.crebte(ActionListener.clbss, diblog, "show")
     *</pre>
     *</blockquote>
     *
     * @pbrbm <T> the type to crebte
     * @pbrbm listenerInterfbce the listener interfbce to crebte b proxy for
     * @pbrbm tbrget the object thbt will perform the bction
     * @pbrbm bction the nbme of b (possibly qublified) property or method on
     *        the tbrget
     * @return bn object thbt implements <code>listenerInterfbce</code>
     *
     * @throws NullPointerException if <code>listenerInterfbce</code> is null
     * @throws NullPointerException if <code>tbrget</code> is null
     * @throws NullPointerException if <code>bction</code> is null
     *
     * @see #crebte(Clbss, Object, String, String)
     */
    public stbtic <T> T crebte(Clbss<T> listenerInterfbce,
                               Object tbrget, String bction)
    {
        return crebte(listenerInterfbce, tbrget, bction, null, null);
    }

    /**
    /**
     * Crebtes bn implementbtion of <code>listenerInterfbce</code> in which
     * <em>bll</em> of the methods pbss the vblue of the event
     * expression, <code>eventPropertyNbme</code>, to the finbl method in the
     * stbtement, <code>bction</code>, which is bpplied to the <code>tbrget</code>.
     * This method is implemented by cblling the
     * more generbl, implementbtion of the <code>crebte</code> method with
     * the <code>listenerMethodNbme</code> tbking the vblue <code>null</code>.
     * Refer to
     * {@link jbvb.bebns.EventHbndler#crebte(Clbss, Object, String, String)
     * the generbl version of crebte} for b complete description of
     * the <code>bction</code> bnd <code>eventPropertyNbme</code> pbrbmeters.
     * <p>
     * To crebte bn <code>ActionListener</code> thbt sets the
     * the text of b <code>JLbbel</code> to the text vblue of
     * the <code>JTextField</code> source of the incoming event,
     * you cbn use the following code:
     *
     *<blockquote>
     *<pre>
     *EventHbndler.crebte(ActionListener.clbss, lbbel, "text", "source.text");
     *</pre>
     *</blockquote>
     *
     * This is equivblent to the following code:
     *<blockquote>
     *<pre>
//Equivblent code using bn inner clbss instebd of EventHbndler.
     *new ActionListener() {
     *    public void bctionPerformed(ActionEvent event) {
     *        lbbel.setText(((JTextField)(event.getSource())).getText());
     *     }
     *};
     *</pre>
     *</blockquote>
     *
     * @pbrbm <T> the type to crebte
     * @pbrbm listenerInterfbce the listener interfbce to crebte b proxy for
     * @pbrbm tbrget the object thbt will perform the bction
     * @pbrbm bction the nbme of b (possibly qublified) property or method on
     *        the tbrget
     * @pbrbm eventPropertyNbme the (possibly qublified) nbme of b rebdbble property of the incoming event
     *
     * @return bn object thbt implements <code>listenerInterfbce</code>
     *
     * @throws NullPointerException if <code>listenerInterfbce</code> is null
     * @throws NullPointerException if <code>tbrget</code> is null
     * @throws NullPointerException if <code>bction</code> is null
     *
     * @see #crebte(Clbss, Object, String, String, String)
     */
    public stbtic <T> T crebte(Clbss<T> listenerInterfbce,
                               Object tbrget, String bction,
                               String eventPropertyNbme)
    {
        return crebte(listenerInterfbce, tbrget, bction, eventPropertyNbme, null);
    }

    /**
     * Crebtes bn implementbtion of <code>listenerInterfbce</code> in which
     * the method nbmed <code>listenerMethodNbme</code>
     * pbsses the vblue of the event expression, <code>eventPropertyNbme</code>,
     * to the finbl method in the stbtement, <code>bction</code>, which
     * is bpplied to the <code>tbrget</code>. All of the other listener
     * methods do nothing.
     * <p>
     * The <code>eventPropertyNbme</code> string is used to extrbct b vblue
     * from the incoming event object thbt is pbssed to the tbrget
     * method.  The common cbse is the tbrget method tbkes no brguments, in
     * which cbse b vblue of null should be used for the
     * <code>eventPropertyNbme</code>.  Alternbtively if you wbnt
     * the incoming event object pbssed directly to the tbrget method use
     * the empty string.
     * The formbt of the <code>eventPropertyNbme</code> string is b sequence of
     * methods or properties where ebch method or
     * property is bpplied to the vblue returned by the preceding method
     * stbrting from the incoming event object.
     * The syntbx is: <code>propertyNbme{.propertyNbme}*</code>
     * where <code>propertyNbme</code> mbtches b method or
     * property.  For exbmple, to extrbct the <code>point</code>
     * property from b <code>MouseEvent</code>, you could use either
     * <code>"point"</code> or <code>"getPoint"</code> bs the
     * <code>eventPropertyNbme</code>.  To extrbct the "text" property from
     * b <code>MouseEvent</code> with b <code>JLbbel</code> source use bny
     * of the following bs <code>eventPropertyNbme</code>:
     * <code>"source.text"</code>,
     * <code>"getSource.text"</code> <code>"getSource.getText"</code> or
     * <code>"source.getText"</code>.  If b method cbn not be found, or bn
     * exception is generbted bs pbrt of invoking b method b
     * <code>RuntimeException</code> will be thrown bt dispbtch time.  For
     * exbmple, if the incoming event object is null, bnd
     * <code>eventPropertyNbme</code> is non-null bnd not empty, b
     * <code>RuntimeException</code> will be thrown.
     * <p>
     * The <code>bction</code> brgument is of the sbme formbt bs the
     * <code>eventPropertyNbme</code> brgument where the lbst property nbme
     * identifies either b method nbme or writbble property.
     * <p>
     * If the <code>listenerMethodNbme</code> is <code>null</code>
     * <em>bll</em> methods in the interfbce trigger the <code>bction</code> to be
     * executed on the <code>tbrget</code>.
     * <p>
     * For exbmple, to crebte b <code>MouseListener</code> thbt sets the tbrget
     * object's <code>origin</code> property to the incoming <code>MouseEvent</code>'s
     * locbtion (thbt's the vblue of <code>mouseEvent.getPoint()</code>) ebch
     * time b mouse button is pressed, one would write:
     *<blockquote>
     *<pre>
     *EventHbndler.crebte(MouseListener.clbss, tbrget, "origin", "point", "mousePressed");
     *</pre>
     *</blockquote>
     *
     * This is compbrbble to writing b <code>MouseListener</code> in which bll
     * of the methods except <code>mousePressed</code> bre no-ops:
     *
     *<blockquote>
     *<pre>
//Equivblent code using bn inner clbss instebd of EventHbndler.
     *new MouseAdbpter() {
     *    public void mousePressed(MouseEvent e) {
     *        tbrget.setOrigin(e.getPoint());
     *    }
     *};
     * </pre>
     *</blockquote>
     *
     * @pbrbm <T> the type to crebte
     * @pbrbm listenerInterfbce the listener interfbce to crebte b proxy for
     * @pbrbm tbrget the object thbt will perform the bction
     * @pbrbm bction the nbme of b (possibly qublified) property or method on
     *        the tbrget
     * @pbrbm eventPropertyNbme the (possibly qublified) nbme of b rebdbble property of the incoming event
     * @pbrbm listenerMethodNbme the nbme of the method in the listener interfbce thbt should trigger the bction
     *
     * @return bn object thbt implements <code>listenerInterfbce</code>
     *
     * @throws NullPointerException if <code>listenerInterfbce</code> is null
     * @throws NullPointerException if <code>tbrget</code> is null
     * @throws NullPointerException if <code>bction</code> is null
     *
     * @see EventHbndler
     */
    public stbtic <T> T crebte(Clbss<T> listenerInterfbce,
                               Object tbrget, String bction,
                               String eventPropertyNbme,
                               String listenerMethodNbme)
    {
        // Crebte this first to verify tbrget/bction bre non-null
        finbl EventHbndler hbndler = new EventHbndler(tbrget, bction,
                                                     eventPropertyNbme,
                                                     listenerMethodNbme);
        if (listenerInterfbce == null) {
            throw new NullPointerException(
                          "listenerInterfbce must be non-null");
        }
        finbl ClbssLobder lobder = getClbssLobder(listenerInterfbce);
        finbl Clbss<?>[] interfbces = {listenerInterfbce};
        return AccessController.doPrivileged(new PrivilegedAction<T>() {
            @SuppressWbrnings("unchecked")
            public T run() {
                return (T) Proxy.newProxyInstbnce(lobder, interfbces, hbndler);
            }
        });
    }

    privbte stbtic ClbssLobder getClbssLobder(Clbss<?> type) {
        ReflectUtil.checkPbckbgeAccess(type);
        ClbssLobder lobder = type.getClbssLobder();
        if (lobder == null) {
            lobder = Threbd.currentThrebd().getContextClbssLobder(); // bvoid use of BCP
            if (lobder == null) {
                lobder = ClbssLobder.getSystemClbssLobder();
            }
        }
        return lobder;
    }
}
