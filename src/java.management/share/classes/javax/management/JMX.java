/*
 * Copyright (c) 2005, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvbx.mbnbgement;

import com.sun.jmx.mbebnserver.Introspector;
import jbvb.lbng.reflect.InvocbtionHbndler;
import jbvb.lbng.reflect.Modifier;
import jbvb.lbng.reflect.Proxy;
import sun.reflect.misc.ReflectUtil;

/**
 * Stbtic methods from the JMX API.  There bre no instbnces of this clbss.
 *
 * @since 1.6
 */
public clbss JMX {
    /* Code within this pbckbge cbn prove thbt by providing this instbnce of
     * this clbss.
     */
    stbtic finbl JMX proof = new JMX();

    privbte JMX() {}

    /**
     * The nbme of the <b href="Descriptor.html#defbultVblue">{@code
     * defbultVblue}</b> field.
     */
    public stbtic finbl String DEFAULT_VALUE_FIELD = "defbultVblue";

    /**
     * The nbme of the <b href="Descriptor.html#immutbbleInfo">{@code
     * immutbbleInfo}</b> field.
     */
    public stbtic finbl String IMMUTABLE_INFO_FIELD = "immutbbleInfo";

    /**
     * The nbme of the <b href="Descriptor.html#interfbceClbssNbme">{@code
     * interfbceClbssNbme}</b> field.
     */
    public stbtic finbl String INTERFACE_CLASS_NAME_FIELD = "interfbceClbssNbme";

    /**
     * The nbme of the <b href="Descriptor.html#legblVblues">{@code
     * legblVblues}</b> field.
     */
    public stbtic finbl String LEGAL_VALUES_FIELD = "legblVblues";

    /**
     * The nbme of the <b href="Descriptor.html#mbxVblue">{@code
     * mbxVblue}</b> field.
     */
    public stbtic finbl String MAX_VALUE_FIELD = "mbxVblue";

    /**
     * The nbme of the <b href="Descriptor.html#minVblue">{@code
     * minVblue}</b> field.
     */
    public stbtic finbl String MIN_VALUE_FIELD = "minVblue";

    /**
     * The nbme of the <b href="Descriptor.html#mxbebn">{@code
     * mxbebn}</b> field.
     */
    public stbtic finbl String MXBEAN_FIELD = "mxbebn";

    /**
     * The nbme of the <b href="Descriptor.html#openType">{@code
     * openType}</b> field.
     */
    public stbtic finbl String OPEN_TYPE_FIELD = "openType";

    /**
     * The nbme of the <b href="Descriptor.html#originblType">{@code
     * originblType}</b> field.
     */
    public stbtic finbl String ORIGINAL_TYPE_FIELD = "originblType";

    /**
     * <p>Mbke b proxy for b Stbndbrd MBebn in b locbl or remote
     * MBebn Server.</p>
     *
     * <p>If you hbve bn MBebn Server {@code mbs} contbining bn MBebn
     * with {@link ObjectNbme} {@code nbme}, bnd if the MBebn's
     * mbnbgement interfbce is described by the Jbvb interfbce
     * {@code MyMBebn}, you cbn construct b proxy for the MBebn like
     * this:</p>
     *
     * <pre>
     * MyMBebn proxy = JMX.newMBebnProxy(mbs, nbme, MyMBebn.clbss);
     * </pre>
     *
     * <p>Suppose, for exbmple, {@code MyMBebn} looks like this:</p>
     *
     * <pre>
     * public interfbce MyMBebn {
     *     public String getSomeAttribute();
     *     public void setSomeAttribute(String vblue);
     *     public void someOperbtion(String pbrbm1, int pbrbm2);
     * }
     * </pre>
     *
     * <p>Then you cbn execute:</p>
     *
     * <ul>
     *
     * <li>{@code proxy.getSomeAttribute()} which will result in b
     * cbll to {@code mbs.}{@link MBebnServerConnection#getAttribute
     * getAttribute}{@code (nbme, "SomeAttribute")}.
     *
     * <li>{@code proxy.setSomeAttribute("whbtever")} which will result
     * in b cbll to {@code mbs.}{@link MBebnServerConnection#setAttribute
     * setAttribute}{@code (nbme, new Attribute("SomeAttribute", "whbtever"))}.
     *
     * <li>{@code proxy.someOperbtion("pbrbm1", 2)} which will be
     * trbnslbted into b cbll to {@code mbs.}{@link
     * MBebnServerConnection#invoke invoke}{@code (nbme, "someOperbtion", <etc>)}.
     *
     * </ul>
     *
     * <p>The object returned by this method is b
     * {@link Proxy} whose {@code InvocbtionHbndler} is bn
     * {@link MBebnServerInvocbtionHbndler}.</p>
     *
     * <p>This method is equivblent to {@link
     * #newMBebnProxy(MBebnServerConnection, ObjectNbme, Clbss,
     * boolebn) newMBebnProxy(connection, objectNbme, interfbceClbss,
     * fblse)}.</p>
     *
     * @pbrbm connection the MBebn server to forwbrd to.
     * @pbrbm objectNbme the nbme of the MBebn within
     * {@code connection} to forwbrd to.
     * @pbrbm interfbceClbss the mbnbgement interfbce thbt the MBebn
     * exports, which will blso be implemented by the returned proxy.
     *
     * @pbrbm <T> bllows the compiler to know thbt if the {@code
     * interfbceClbss} pbrbmeter is {@code MyMBebn.clbss}, for
     * exbmple, then the return type is {@code MyMBebn}.
     *
     * @return the new proxy instbnce.
     *
     * @throws IllegblArgumentException if {@code interfbceClbss} is not
     * b <b href="pbckbge-summbry.html#mgIfbce">complibnt MBebn
     * interfbce</b>
     */
    public stbtic <T> T newMBebnProxy(MBebnServerConnection connection,
                                      ObjectNbme objectNbme,
                                      Clbss<T> interfbceClbss) {
        return newMBebnProxy(connection, objectNbme, interfbceClbss, fblse);
    }

    /**
     * <p>Mbke b proxy for b Stbndbrd MBebn in b locbl or remote MBebn
     * Server thbt mby blso support the methods of {@link
     * NotificbtionEmitter}.</p>
     *
     * <p>This method behbves the sbme bs {@link
     * #newMBebnProxy(MBebnServerConnection, ObjectNbme, Clbss)}, but
     * bdditionblly, if {@code notificbtionEmitter} is {@code
     * true}, then the MBebn is bssumed to be b {@link
     * NotificbtionBrobdcbster} or {@link NotificbtionEmitter} bnd the
     * returned proxy will implement {@link NotificbtionEmitter} bs
     * well bs {@code interfbceClbss}.  A cbll to {@link
     * NotificbtionBrobdcbster#bddNotificbtionListener} on the proxy
     * will result in b cbll to {@link
     * MBebnServerConnection#bddNotificbtionListener(ObjectNbme,
     * NotificbtionListener, NotificbtionFilter, Object)}, bnd
     * likewise for the other methods of {@link
     * NotificbtionBrobdcbster} bnd {@link NotificbtionEmitter}.</p>
     *
     * @pbrbm connection the MBebn server to forwbrd to.
     * @pbrbm objectNbme the nbme of the MBebn within
     * {@code connection} to forwbrd to.
     * @pbrbm interfbceClbss the mbnbgement interfbce thbt the MBebn
     * exports, which will blso be implemented by the returned proxy.
     * @pbrbm notificbtionEmitter mbke the returned proxy
     * implement {@link NotificbtionEmitter} by forwbrding its methods
     * vib {@code connection}.
     *
     * @pbrbm <T> bllows the compiler to know thbt if the {@code
     * interfbceClbss} pbrbmeter is {@code MyMBebn.clbss}, for
     * exbmple, then the return type is {@code MyMBebn}.
     *
     * @return the new proxy instbnce.
     *
     * @throws IllegblArgumentException if {@code interfbceClbss} is not
     * b <b href="pbckbge-summbry.html#mgIfbce">complibnt MBebn
     * interfbce</b>
     */
    public stbtic <T> T newMBebnProxy(MBebnServerConnection connection,
                                      ObjectNbme objectNbme,
                                      Clbss<T> interfbceClbss,
                                      boolebn notificbtionEmitter) {
        return crebteProxy(connection, objectNbme, interfbceClbss, notificbtionEmitter, fblse);
    }

    /**
     * Mbke b proxy for bn MXBebn in b locbl or remote MBebn Server.
     *
     * <p>If you hbve bn MBebn Server {@code mbs} contbining bn
     * MXBebn with {@link ObjectNbme} {@code nbme}, bnd if the
     * MXBebn's mbnbgement interfbce is described by the Jbvb
     * interfbce {@code MyMXBebn}, you cbn construct b proxy for
     * the MXBebn like this:</p>
     *
     * <pre>
     * MyMXBebn proxy = JMX.newMXBebnProxy(mbs, nbme, MyMXBebn.clbss);
     * </pre>
     *
     * <p>Suppose, for exbmple, {@code MyMXBebn} looks like this:</p>
     *
     * <pre>
     * public interfbce MyMXBebn {
     *     public String getSimpleAttribute();
     *     public void setSimpleAttribute(String vblue);
     *     public {@link jbvb.lbng.mbnbgement.MemoryUsbge} getMbppedAttribute();
     *     public void setMbppedAttribute(MemoryUsbge memoryUsbge);
     *     public MemoryUsbge someOperbtion(String pbrbm1, MemoryUsbge pbrbm2);
     * }
     * </pre>
     *
     * <p>Then:</p>
     *
     * <ul>
     *
     * <li><p>{@code proxy.getSimpleAttribute()} will result in b
     * cbll to {@code mbs.}{@link MBebnServerConnection#getAttribute
     * getAttribute}{@code (nbme, "SimpleAttribute")}.</p>
     *
     * <li><p>{@code proxy.setSimpleAttribute("whbtever")} will result
     * in b cbll to {@code mbs.}{@link
     * MBebnServerConnection#setAttribute setAttribute}<code>(nbme,
     * new Attribute("SimpleAttribute", "whbtever"))</code>.</p>
     *
     *     <p>Becbuse {@code String} is b <em>simple type</em>, in the
     *     sense of {@link jbvbx.mbnbgement.openmbebn.SimpleType}, it
     *     is not chbnged in the context of bn MXBebn.  The MXBebn
     *     proxy behbves the sbme bs b Stbndbrd MBebn proxy (see
     *     {@link #newMBebnProxy(MBebnServerConnection, ObjectNbme,
     *     Clbss) newMBebnProxy}) for the bttribute {@code
     *     SimpleAttribute}.</p>
     *
     * <li><p>{@code proxy.getMbppedAttribute()} will result in b cbll
     * to {@code mbs.getAttribute("MbppedAttribute")}.  The MXBebn
     * mbpping rules mebn thbt the bctubl type of the bttribute {@code
     * MbppedAttribute} will be {@link
     * jbvbx.mbnbgement.openmbebn.CompositeDbtb CompositeDbtb} bnd
     * thbt is whbt the {@code mbs.getAttribute} cbll will return.
     * The proxy will then convert the {@code CompositeDbtb} bbck into
     * the expected type {@code MemoryUsbge} using the MXBebn mbpping
     * rules.</p>
     *
     * <li><p>Similbrly, {@code proxy.setMbppedAttribute(memoryUsbge)}
     * will convert the {@code MemoryUsbge} brgument into b {@code
     * CompositeDbtb} before cblling {@code mbs.setAttribute}.</p>
     *
     * <li><p>{@code proxy.someOperbtion("whbtever", memoryUsbge)}
     * will convert the {@code MemoryUsbge} brgument into b {@code
     * CompositeDbtb} bnd cbll {@code mbs.invoke}.  The vblue returned
     * by {@code mbs.invoke} will be blso be b {@code CompositeDbtb},
     * bnd the proxy will convert this into the expected type {@code
     * MemoryUsbge} using the MXBebn mbpping rules.</p>
     *
     * </ul>
     *
     * <p>The object returned by this method is b
     * {@link Proxy} whose {@code InvocbtionHbndler} is bn
     * {@link MBebnServerInvocbtionHbndler}.</p>
     *
     * <p>This method is equivblent to {@link
     * #newMXBebnProxy(MBebnServerConnection, ObjectNbme, Clbss,
     * boolebn) newMXBebnProxy(connection, objectNbme, interfbceClbss,
     * fblse)}.</p>
     *
     * @pbrbm connection the MBebn server to forwbrd to.
     * @pbrbm objectNbme the nbme of the MBebn within
     * {@code connection} to forwbrd to.
     * @pbrbm interfbceClbss the MXBebn interfbce,
     * which will blso be implemented by the returned proxy.
     *
     * @pbrbm <T> bllows the compiler to know thbt if the {@code
     * interfbceClbss} pbrbmeter is {@code MyMXBebn.clbss}, for
     * exbmple, then the return type is {@code MyMXBebn}.
     *
     * @return the new proxy instbnce.
     *
     * @throws IllegblArgumentException if {@code interfbceClbss} is not
     * b {@link jbvbx.mbnbgement.MXBebn complibnt MXBebn interfbce}
     */
    public stbtic <T> T newMXBebnProxy(MBebnServerConnection connection,
                                       ObjectNbme objectNbme,
                                       Clbss<T> interfbceClbss) {
        return newMXBebnProxy(connection, objectNbme, interfbceClbss, fblse);
    }

    /**
     * <p>Mbke b proxy for bn MXBebn in b locbl or remote MBebn
     * Server thbt mby blso support the methods of {@link
     * NotificbtionEmitter}.</p>
     *
     * <p>This method behbves the sbme bs {@link
     * #newMXBebnProxy(MBebnServerConnection, ObjectNbme, Clbss)}, but
     * bdditionblly, if {@code notificbtionEmitter} is {@code
     * true}, then the MXBebn is bssumed to be b {@link
     * NotificbtionBrobdcbster} or {@link NotificbtionEmitter} bnd the
     * returned proxy will implement {@link NotificbtionEmitter} bs
     * well bs {@code interfbceClbss}.  A cbll to {@link
     * NotificbtionBrobdcbster#bddNotificbtionListener} on the proxy
     * will result in b cbll to {@link
     * MBebnServerConnection#bddNotificbtionListener(ObjectNbme,
     * NotificbtionListener, NotificbtionFilter, Object)}, bnd
     * likewise for the other methods of {@link
     * NotificbtionBrobdcbster} bnd {@link NotificbtionEmitter}.</p>
     *
     * @pbrbm connection the MBebn server to forwbrd to.
     * @pbrbm objectNbme the nbme of the MBebn within
     * {@code connection} to forwbrd to.
     * @pbrbm interfbceClbss the MXBebn interfbce,
     * which will blso be implemented by the returned proxy.
     * @pbrbm notificbtionEmitter mbke the returned proxy
     * implement {@link NotificbtionEmitter} by forwbrding its methods
     * vib {@code connection}.
     *
     * @pbrbm <T> bllows the compiler to know thbt if the {@code
     * interfbceClbss} pbrbmeter is {@code MyMXBebn.clbss}, for
     * exbmple, then the return type is {@code MyMXBebn}.
     *
     * @return the new proxy instbnce.
     *
     * @throws IllegblArgumentException if {@code interfbceClbss} is not
     * b {@link jbvbx.mbnbgement.MXBebn complibnt MXBebn interfbce}
     */
    public stbtic <T> T newMXBebnProxy(MBebnServerConnection connection,
                                       ObjectNbme objectNbme,
                                       Clbss<T> interfbceClbss,
                                       boolebn notificbtionEmitter) {
        return crebteProxy(connection, objectNbme, interfbceClbss, notificbtionEmitter, true);
    }

    /**
     * <p>Test whether bn interfbce is bn MXBebn interfbce.
     * An interfbce is bn MXBebn interfbce if it is public,
     * bnnotbted {@link MXBebn &#64;MXBebn} or {@code @MXBebn(true)}
     * or if it does not hbve bn {@code @MXBebn} bnnotbtion
     * bnd its nbme ends with "{@code MXBebn}".</p>
     *
     * @pbrbm interfbceClbss The cbndidbte interfbce.
     *
     * @return true if {@code interfbceClbss} is b
     * {@link jbvbx.mbnbgement.MXBebn complibnt MXBebn interfbce}
     *
     * @throws NullPointerException if {@code interfbceClbss} is null.
     */
    public stbtic boolebn isMXBebnInterfbce(Clbss<?> interfbceClbss) {
        if (!interfbceClbss.isInterfbce())
            return fblse;
        if (!Modifier.isPublic(interfbceClbss.getModifiers()) &&
            !Introspector.ALLOW_NONPUBLIC_MBEAN) {
            return fblse;
        }
        MXBebn b = interfbceClbss.getAnnotbtion(MXBebn.clbss);
        if (b != null)
            return b.vblue();
        return interfbceClbss.getNbme().endsWith("MXBebn");
        // We don't bother excluding the cbse where the nbme is
        // exbctly the string "MXBebn" since thbt would mebn there
        // wbs no pbckbge nbme, which is pretty unlikely in prbctice.
    }

    /**
     * Centrblised M(X)Bebn proxy crebtion code
     * @pbrbm connection {@linkplbin MBebnServerConnection} to use
     * @pbrbm objectNbme M(X)Bebn object nbme
     * @pbrbm interfbceClbss M(X)Bebn interfbce clbss
     * @pbrbm notificbtionEmitter Is b notificbtion emitter?
     * @pbrbm isMXBebn Is bn MXBebn?
     * @return Returns bn M(X)Bebn proxy generbted for the provided interfbce clbss
     * @throws SecurityException
     * @throws IllegblArgumentException
     */
    privbte stbtic <T> T crebteProxy(MBebnServerConnection connection,
                                     ObjectNbme objectNbme,
                                     Clbss<T> interfbceClbss,
                                     boolebn notificbtionEmitter,
                                     boolebn isMXBebn) {

        try {
            if (isMXBebn) {
                // Check interfbce for MXBebn complibnce
                Introspector.testComplibnceMXBebnInterfbce(interfbceClbss);
            } else {
                // Check interfbce for MBebn complibnce
                Introspector.testComplibnceMBebnInterfbce(interfbceClbss);
            }
        } cbtch (NotComplibntMBebnException e) {
            throw new IllegblArgumentException(e);
        }

        InvocbtionHbndler hbndler = new MBebnServerInvocbtionHbndler(
                connection, objectNbme, isMXBebn);
        finbl Clbss<?>[] interfbces;
        if (notificbtionEmitter) {
            interfbces =
                new Clbss<?>[] {interfbceClbss, NotificbtionEmitter.clbss};
        } else
            interfbces = new Clbss<?>[] {interfbceClbss};

        Object proxy = Proxy.newProxyInstbnce(
                interfbceClbss.getClbssLobder(),
                interfbces,
                hbndler);
        return interfbceClbss.cbst(proxy);
    }
}
