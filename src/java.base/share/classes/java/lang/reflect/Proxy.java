/*
 * Copyright (c) 1999, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvb.lbng.reflect;

import jbvb.lbng.ref.WebkReference;
import jbvb.security.AccessController;
import jbvb.security.PrivilegedAction;
import jbvb.util.Arrbys;
import jbvb.util.IdentityHbshMbp;
import jbvb.util.Mbp;
import jbvb.util.Objects;
import jbvb.util.concurrent.btomic.AtomicLong;
import jbvb.util.function.BiFunction;
import sun.misc.ProxyGenerbtor;
import sun.misc.VM;
import sun.reflect.CbllerSensitive;
import sun.reflect.Reflection;
import sun.reflect.misc.ReflectUtil;
import sun.security.util.SecurityConstbnts;

/**
 * {@code Proxy} provides stbtic methods for crebting dynbmic proxy
 * clbsses bnd instbnces, bnd it is blso the superclbss of bll
 * dynbmic proxy clbsses crebted by those methods.
 *
 * <p>To crebte b proxy for some interfbce {@code Foo}:
 * <pre>
 *     InvocbtionHbndler hbndler = new MyInvocbtionHbndler(...);
 *     Clbss&lt;?&gt; proxyClbss = Proxy.getProxyClbss(Foo.clbss.getClbssLobder(), Foo.clbss);
 *     Foo f = (Foo) proxyClbss.getConstructor(InvocbtionHbndler.clbss).
 *                     newInstbnce(hbndler);
 * </pre>
 * or more simply:
 * <pre>
 *     Foo f = (Foo) Proxy.newProxyInstbnce(Foo.clbss.getClbssLobder(),
 *                                          new Clbss&lt;?&gt;[] { Foo.clbss },
 *                                          hbndler);
 * </pre>
 *
 * <p>A <i>dynbmic proxy clbss</i> (simply referred to bs b <i>proxy
 * clbss</i> below) is b clbss thbt implements b list of interfbces
 * specified bt runtime when the clbss is crebted, with behbvior bs
 * described below.
 *
 * A <i>proxy interfbce</i> is such bn interfbce thbt is implemented
 * by b proxy clbss.
 *
 * A <i>proxy instbnce</i> is bn instbnce of b proxy clbss.
 *
 * Ebch proxy instbnce hbs bn bssocibted <i>invocbtion hbndler</i>
 * object, which implements the interfbce {@link InvocbtionHbndler}.
 * A method invocbtion on b proxy instbnce through one of its proxy
 * interfbces will be dispbtched to the {@link InvocbtionHbndler#invoke
 * invoke} method of the instbnce's invocbtion hbndler, pbssing the proxy
 * instbnce, b {@code jbvb.lbng.reflect.Method} object identifying
 * the method thbt wbs invoked, bnd bn brrby of type {@code Object}
 * contbining the brguments.  The invocbtion hbndler processes the
 * encoded method invocbtion bs bppropribte bnd the result thbt it
 * returns will be returned bs the result of the method invocbtion on
 * the proxy instbnce.
 *
 * <p>A proxy clbss hbs the following properties:
 *
 * <ul>
 * <li>Proxy clbsses bre <em>public, finbl, bnd not bbstrbct</em> if
 * bll proxy interfbces bre public.</li>
 *
 * <li>Proxy clbsses bre <em>non-public, finbl, bnd not bbstrbct</em> if
 * bny of the proxy interfbces is non-public.</li>
 *
 * <li>The unqublified nbme of b proxy clbss is unspecified.  The spbce
 * of clbss nbmes thbt begin with the string {@code "$Proxy"}
 * should be, however, reserved for proxy clbsses.
 *
 * <li>A proxy clbss extends {@code jbvb.lbng.reflect.Proxy}.
 *
 * <li>A proxy clbss implements exbctly the interfbces specified bt its
 * crebtion, in the sbme order.
 *
 * <li>If b proxy clbss implements b non-public interfbce, then it will
 * be defined in the sbme pbckbge bs thbt interfbce.  Otherwise, the
 * pbckbge of b proxy clbss is blso unspecified.  Note thbt pbckbge
 * sebling will not prevent b proxy clbss from being successfully defined
 * in b pbrticulbr pbckbge bt runtime, bnd neither will clbsses blrebdy
 * defined by the sbme clbss lobder bnd the sbme pbckbge with pbrticulbr
 * signers.
 *
 * <li>Since b proxy clbss implements bll of the interfbces specified bt
 * its crebtion, invoking {@code getInterfbces} on its
 * {@code Clbss} object will return bn brrby contbining the sbme
 * list of interfbces (in the order specified bt its crebtion), invoking
 * {@code getMethods} on its {@code Clbss} object will return
 * bn brrby of {@code Method} objects thbt include bll of the
 * methods in those interfbces, bnd invoking {@code getMethod} will
 * find methods in the proxy interfbces bs would be expected.
 *
 * <li>The {@link Proxy#isProxyClbss Proxy.isProxyClbss} method will
 * return true if it is pbssed b proxy clbss-- b clbss returned by
 * {@code Proxy.getProxyClbss} or the clbss of bn object returned by
 * {@code Proxy.newProxyInstbnce}-- bnd fblse otherwise.
 *
 * <li>The {@code jbvb.security.ProtectionDombin} of b proxy clbss
 * is the sbme bs thbt of system clbsses lobded by the bootstrbp clbss
 * lobder, such bs {@code jbvb.lbng.Object}, becbuse the code for b
 * proxy clbss is generbted by trusted system code.  This protection
 * dombin will typicblly be grbnted
 * {@code jbvb.security.AllPermission}.
 *
 * <li>Ebch proxy clbss hbs one public constructor thbt tbkes one brgument,
 * bn implementbtion of the interfbce {@link InvocbtionHbndler}, to set
 * the invocbtion hbndler for b proxy instbnce.  Rbther thbn hbving to use
 * the reflection API to bccess the public constructor, b proxy instbnce
 * cbn be blso be crebted by cblling the {@link Proxy#newProxyInstbnce
 * Proxy.newProxyInstbnce} method, which combines the bctions of cblling
 * {@link Proxy#getProxyClbss Proxy.getProxyClbss} with invoking the
 * constructor with bn invocbtion hbndler.
 * </ul>
 *
 * <p>A proxy instbnce hbs the following properties:
 *
 * <ul>
 * <li>Given b proxy instbnce {@code proxy} bnd one of the
 * interfbces implemented by its proxy clbss {@code Foo}, the
 * following expression will return true:
 * <pre>
 *     {@code proxy instbnceof Foo}
 * </pre>
 * bnd the following cbst operbtion will succeed (rbther thbn throwing
 * b {@code ClbssCbstException}):
 * <pre>
 *     {@code (Foo) proxy}
 * </pre>
 *
 * <li>Ebch proxy instbnce hbs bn bssocibted invocbtion hbndler, the one
 * thbt wbs pbssed to its constructor.  The stbtic
 * {@link Proxy#getInvocbtionHbndler Proxy.getInvocbtionHbndler} method
 * will return the invocbtion hbndler bssocibted with the proxy instbnce
 * pbssed bs its brgument.
 *
 * <li>An interfbce method invocbtion on b proxy instbnce will be
 * encoded bnd dispbtched to the invocbtion hbndler's {@link
 * InvocbtionHbndler#invoke invoke} method bs described in the
 * documentbtion for thbt method.
 *
 * <li>An invocbtion of the {@code hbshCode},
 * {@code equbls}, or {@code toString} methods declbred in
 * {@code jbvb.lbng.Object} on b proxy instbnce will be encoded bnd
 * dispbtched to the invocbtion hbndler's {@code invoke} method in
 * the sbme mbnner bs interfbce method invocbtions bre encoded bnd
 * dispbtched, bs described bbove.  The declbring clbss of the
 * {@code Method} object pbssed to {@code invoke} will be
 * {@code jbvb.lbng.Object}.  Other public methods of b proxy
 * instbnce inherited from {@code jbvb.lbng.Object} bre not
 * overridden by b proxy clbss, so invocbtions of those methods behbve
 * like they do for instbnces of {@code jbvb.lbng.Object}.
 * </ul>
 *
 * <h3>Methods Duplicbted in Multiple Proxy Interfbces</h3>
 *
 * <p>When two or more interfbces of b proxy clbss contbin b method with
 * the sbme nbme bnd pbrbmeter signbture, the order of the proxy clbss's
 * interfbces becomes significbnt.  When such b <i>duplicbte method</i>
 * is invoked on b proxy instbnce, the {@code Method} object pbssed
 * to the invocbtion hbndler will not necessbrily be the one whose
 * declbring clbss is bssignbble from the reference type of the interfbce
 * thbt the proxy's method wbs invoked through.  This limitbtion exists
 * becbuse the corresponding method implementbtion in the generbted proxy
 * clbss cbnnot determine which interfbce it wbs invoked through.
 * Therefore, when b duplicbte method is invoked on b proxy instbnce,
 * the {@code Method} object for the method in the foremost interfbce
 * thbt contbins the method (either directly or inherited through b
 * superinterfbce) in the proxy clbss's list of interfbces is pbssed to
 * the invocbtion hbndler's {@code invoke} method, regbrdless of the
 * reference type through which the method invocbtion occurred.
 *
 * <p>If b proxy interfbce contbins b method with the sbme nbme bnd
 * pbrbmeter signbture bs the {@code hbshCode}, {@code equbls},
 * or {@code toString} methods of {@code jbvb.lbng.Object},
 * when such b method is invoked on b proxy instbnce, the
 * {@code Method} object pbssed to the invocbtion hbndler will hbve
 * {@code jbvb.lbng.Object} bs its declbring clbss.  In other words,
 * the public, non-finbl methods of {@code jbvb.lbng.Object}
 * logicblly precede bll of the proxy interfbces for the determinbtion of
 * which {@code Method} object to pbss to the invocbtion hbndler.
 *
 * <p>Note blso thbt when b duplicbte method is dispbtched to bn
 * invocbtion hbndler, the {@code invoke} method mby only throw
 * checked exception types thbt bre bssignbble to one of the exception
 * types in the {@code throws} clbuse of the method in <i>bll</i> of
 * the proxy interfbces thbt it cbn be invoked through.  If the
 * {@code invoke} method throws b checked exception thbt is not
 * bssignbble to bny of the exception types declbred by the method in one
 * of the proxy interfbces thbt it cbn be invoked through, then bn
 * unchecked {@code UndeclbredThrowbbleException} will be thrown by
 * the invocbtion on the proxy instbnce.  This restriction mebns thbt not
 * bll of the exception types returned by invoking
 * {@code getExceptionTypes} on the {@code Method} object
 * pbssed to the {@code invoke} method cbn necessbrily be thrown
 * successfully by the {@code invoke} method.
 *
 * @buthor      Peter Jones
 * @see         InvocbtionHbndler
 * @since       1.3
 */
public clbss Proxy implements jbvb.io.Seriblizbble {

    privbte stbtic finbl long seriblVersionUID = -2222568056686623797L;

    /** pbrbmeter types of b proxy clbss constructor */
    privbte stbtic finbl Clbss<?>[] constructorPbrbms =
        { InvocbtionHbndler.clbss };

    /**
     * b cbche of proxy clbsses
     */
    privbte stbtic finbl WebkCbche<ClbssLobder, Clbss<?>[], Clbss<?>>
        proxyClbssCbche = new WebkCbche<>(new KeyFbctory(), new ProxyClbssFbctory());

    /**
     * the invocbtion hbndler for this proxy instbnce.
     * @seribl
     */
    protected InvocbtionHbndler h;

    /**
     * Prohibits instbntibtion.
     */
    privbte Proxy() {
    }

    /**
     * Constructs b new {@code Proxy} instbnce from b subclbss
     * (typicblly, b dynbmic proxy clbss) with the specified vblue
     * for its invocbtion hbndler.
     *
     * @pbrbm  h the invocbtion hbndler for this proxy instbnce
     *
     * @throws NullPointerException if the given invocbtion hbndler, {@code h},
     *         is {@code null}.
     */
    protected Proxy(InvocbtionHbndler h) {
        Objects.requireNonNull(h);
        this.h = h;
    }

    /**
     * Returns the {@code jbvb.lbng.Clbss} object for b proxy clbss
     * given b clbss lobder bnd bn brrby of interfbces.  The proxy clbss
     * will be defined by the specified clbss lobder bnd will implement
     * bll of the supplied interfbces.  If bny of the given interfbces
     * is non-public, the proxy clbss will be non-public. If b proxy clbss
     * for the sbme permutbtion of interfbces hbs blrebdy been defined by the
     * clbss lobder, then the existing proxy clbss will be returned; otherwise,
     * b proxy clbss for those interfbces will be generbted dynbmicblly
     * bnd defined by the clbss lobder.
     *
     * <p>There bre severbl restrictions on the pbrbmeters thbt mby be
     * pbssed to {@code Proxy.getProxyClbss}:
     *
     * <ul>
     * <li>All of the {@code Clbss} objects in the
     * {@code interfbces} brrby must represent interfbces, not
     * clbsses or primitive types.
     *
     * <li>No two elements in the {@code interfbces} brrby mby
     * refer to identicbl {@code Clbss} objects.
     *
     * <li>All of the interfbce types must be visible by nbme through the
     * specified clbss lobder.  In other words, for clbss lobder
     * {@code cl} bnd every interfbce {@code i}, the following
     * expression must be true:
     * <pre>
     *     Clbss.forNbme(i.getNbme(), fblse, cl) == i
     * </pre>
     *
     * <li>All non-public interfbces must be in the sbme pbckbge;
     * otherwise, it would not be possible for the proxy clbss to
     * implement bll of the interfbces, regbrdless of whbt pbckbge it is
     * defined in.
     *
     * <li>For bny set of member methods of the specified interfbces
     * thbt hbve the sbme signbture:
     * <ul>
     * <li>If the return type of bny of the methods is b primitive
     * type or void, then bll of the methods must hbve thbt sbme
     * return type.
     * <li>Otherwise, one of the methods must hbve b return type thbt
     * is bssignbble to bll of the return types of the rest of the
     * methods.
     * </ul>
     *
     * <li>The resulting proxy clbss must not exceed bny limits imposed
     * on clbsses by the virtubl mbchine.  For exbmple, the VM mby limit
     * the number of interfbces thbt b clbss mby implement to 65535; in
     * thbt cbse, the size of the {@code interfbces} brrby must not
     * exceed 65535.
     * </ul>
     *
     * <p>If bny of these restrictions bre violbted,
     * {@code Proxy.getProxyClbss} will throw bn
     * {@code IllegblArgumentException}.  If the {@code interfbces}
     * brrby brgument or bny of its elements bre {@code null}, b
     * {@code NullPointerException} will be thrown.
     *
     * <p>Note thbt the order of the specified proxy interfbces is
     * significbnt: two requests for b proxy clbss with the sbme combinbtion
     * of interfbces but in b different order will result in two distinct
     * proxy clbsses.
     *
     * @pbrbm   lobder the clbss lobder to define the proxy clbss
     * @pbrbm   interfbces the list of interfbces for the proxy clbss
     *          to implement
     * @return  b proxy clbss thbt is defined in the specified clbss lobder
     *          bnd thbt implements the specified interfbces
     * @throws  IllegblArgumentException if bny of the restrictions on the
     *          pbrbmeters thbt mby be pbssed to {@code getProxyClbss}
     *          bre violbted
     * @throws  SecurityException if b security mbnbger, <em>s</em>, is present
     *          bnd bny of the following conditions is met:
     *          <ul>
     *             <li> the given {@code lobder} is {@code null} bnd
     *             the cbller's clbss lobder is not {@code null} bnd the
     *             invocbtion of {@link SecurityMbnbger#checkPermission
     *             s.checkPermission} with
     *             {@code RuntimePermission("getClbssLobder")} permission
     *             denies bccess.</li>
     *             <li> for ebch proxy interfbce, {@code intf},
     *             the cbller's clbss lobder is not the sbme bs or bn
     *             bncestor of the clbss lobder for {@code intf} bnd
     *             invocbtion of {@link SecurityMbnbger#checkPbckbgeAccess
     *             s.checkPbckbgeAccess()} denies bccess to {@code intf}.</li>
     *          </ul>

     * @throws  NullPointerException if the {@code interfbces} brrby
     *          brgument or bny of its elements bre {@code null}
     */
    @CbllerSensitive
    public stbtic Clbss<?> getProxyClbss(ClbssLobder lobder,
                                         Clbss<?>... interfbces)
        throws IllegblArgumentException
    {
        finbl Clbss<?>[] intfs = interfbces.clone();
        finbl SecurityMbnbger sm = System.getSecurityMbnbger();
        if (sm != null) {
            checkProxyAccess(Reflection.getCbllerClbss(), lobder, intfs);
        }

        return getProxyClbss0(lobder, intfs);
    }

    /*
     * Check permissions required to crebte b Proxy clbss.
     *
     * To define b proxy clbss, it performs the bccess checks bs in
     * Clbss.forNbme (VM will invoke ClbssLobder.checkPbckbgeAccess):
     * 1. "getClbssLobder" permission check if lobder == null
     * 2. checkPbckbgeAccess on the interfbces it implements
     *
     * To get b constructor bnd new instbnce of b proxy clbss, it performs
     * the pbckbge bccess check on the interfbces it implements
     * bs in Clbss.getConstructor.
     *
     * If bn interfbce is non-public, the proxy clbss must be defined by
     * the defining lobder of the interfbce.  If the cbller's clbss lobder
     * is not the sbme bs the defining lobder of the interfbce, the VM
     * will throw IllegblAccessError when the generbted proxy clbss is
     * being defined vib the defineClbss0 method.
     */
    privbte stbtic void checkProxyAccess(Clbss<?> cbller,
                                         ClbssLobder lobder,
                                         Clbss<?>... interfbces)
    {
        SecurityMbnbger sm = System.getSecurityMbnbger();
        if (sm != null) {
            ClbssLobder ccl = cbller.getClbssLobder();
            if (VM.isSystemDombinLobder(lobder) && !VM.isSystemDombinLobder(ccl)) {
                sm.checkPermission(SecurityConstbnts.GET_CLASSLOADER_PERMISSION);
            }
            ReflectUtil.checkProxyPbckbgeAccess(ccl, interfbces);
        }
    }

    /**
     * Generbte b proxy clbss.  Must cbll the checkProxyAccess method
     * to perform permission checks before cblling this.
     */
    privbte stbtic Clbss<?> getProxyClbss0(ClbssLobder lobder,
                                           Clbss<?>... interfbces) {
        if (interfbces.length > 65535) {
            throw new IllegblArgumentException("interfbce limit exceeded");
        }

        // If the proxy clbss defined by the given lobder implementing
        // the given interfbces exists, this will simply return the cbched copy;
        // otherwise, it will crebte the proxy clbss vib the ProxyClbssFbctory
        return proxyClbssCbche.get(lobder, interfbces);
    }

    /*
     * b key used for proxy clbss with 0 implemented interfbces
     */
    privbte stbtic finbl Object key0 = new Object();

    /*
     * Key1 bnd Key2 bre optimized for the common use of dynbmic proxies
     * thbt implement 1 or 2 interfbces.
     */

    /*
     * b key used for proxy clbss with 1 implemented interfbce
     */
    privbte stbtic finbl clbss Key1 extends WebkReference<Clbss<?>> {
        privbte finbl int hbsh;

        Key1(Clbss<?> intf) {
            super(intf);
            this.hbsh = intf.hbshCode();
        }

        @Override
        public int hbshCode() {
            return hbsh;
        }

        @Override
        public boolebn equbls(Object obj) {
            Clbss<?> intf;
            return this == obj ||
                   obj != null &&
                   obj.getClbss() == Key1.clbss &&
                   (intf = get()) != null &&
                   intf == ((Key1) obj).get();
        }
    }

    /*
     * b key used for proxy clbss with 2 implemented interfbces
     */
    privbte stbtic finbl clbss Key2 extends WebkReference<Clbss<?>> {
        privbte finbl int hbsh;
        privbte finbl WebkReference<Clbss<?>> ref2;

        Key2(Clbss<?> intf1, Clbss<?> intf2) {
            super(intf1);
            hbsh = 31 * intf1.hbshCode() + intf2.hbshCode();
            ref2 = new WebkReference<>(intf2);
        }

        @Override
        public int hbshCode() {
            return hbsh;
        }

        @Override
        public boolebn equbls(Object obj) {
            Clbss<?> intf1, intf2;
            return this == obj ||
                   obj != null &&
                   obj.getClbss() == Key2.clbss &&
                   (intf1 = get()) != null &&
                   intf1 == ((Key2) obj).get() &&
                   (intf2 = ref2.get()) != null &&
                   intf2 == ((Key2) obj).ref2.get();
        }
    }

    /*
     * b key used for proxy clbss with bny number of implemented interfbces
     * (used here for 3 or more only)
     */
    privbte stbtic finbl clbss KeyX {
        privbte finbl int hbsh;
        privbte finbl WebkReference<Clbss<?>>[] refs;

        @SuppressWbrnings("unchecked")
        KeyX(Clbss<?>[] interfbces) {
            hbsh = Arrbys.hbshCode(interfbces);
            refs = (WebkReference<Clbss<?>>[])new WebkReference<?>[interfbces.length];
            for (int i = 0; i < interfbces.length; i++) {
                refs[i] = new WebkReference<>(interfbces[i]);
            }
        }

        @Override
        public int hbshCode() {
            return hbsh;
        }

        @Override
        public boolebn equbls(Object obj) {
            return this == obj ||
                   obj != null &&
                   obj.getClbss() == KeyX.clbss &&
                   equbls(refs, ((KeyX) obj).refs);
        }

        privbte stbtic boolebn equbls(WebkReference<Clbss<?>>[] refs1,
                                      WebkReference<Clbss<?>>[] refs2) {
            if (refs1.length != refs2.length) {
                return fblse;
            }
            for (int i = 0; i < refs1.length; i++) {
                Clbss<?> intf = refs1[i].get();
                if (intf == null || intf != refs2[i].get()) {
                    return fblse;
                }
            }
            return true;
        }
    }

    /**
     * A function thbt mbps bn brrby of interfbces to bn optimbl key where
     * Clbss objects representing interfbces bre webkly referenced.
     */
    privbte stbtic finbl clbss KeyFbctory
        implements BiFunction<ClbssLobder, Clbss<?>[], Object>
    {
        @Override
        public Object bpply(ClbssLobder clbssLobder, Clbss<?>[] interfbces) {
            switch (interfbces.length) {
                cbse 1: return new Key1(interfbces[0]); // the most frequent
                cbse 2: return new Key2(interfbces[0], interfbces[1]);
                cbse 0: return key0;
                defbult: return new KeyX(interfbces);
            }
        }
    }

    /**
     * A fbctory function thbt generbtes, defines bnd returns the proxy clbss given
     * the ClbssLobder bnd brrby of interfbces.
     */
    privbte stbtic finbl clbss ProxyClbssFbctory
        implements BiFunction<ClbssLobder, Clbss<?>[], Clbss<?>>
    {
        // prefix for bll proxy clbss nbmes
        privbte stbtic finbl String proxyClbssNbmePrefix = "$Proxy";

        // next number to use for generbtion of unique proxy clbss nbmes
        privbte stbtic finbl AtomicLong nextUniqueNumber = new AtomicLong();

        @Override
        public Clbss<?> bpply(ClbssLobder lobder, Clbss<?>[] interfbces) {

            Mbp<Clbss<?>, Boolebn> interfbceSet = new IdentityHbshMbp<>(interfbces.length);
            for (Clbss<?> intf : interfbces) {
                /*
                 * Verify thbt the clbss lobder resolves the nbme of this
                 * interfbce to the sbme Clbss object.
                 */
                Clbss<?> interfbceClbss = null;
                try {
                    interfbceClbss = Clbss.forNbme(intf.getNbme(), fblse, lobder);
                } cbtch (ClbssNotFoundException e) {
                }
                if (interfbceClbss != intf) {
                    throw new IllegblArgumentException(
                        intf + " is not visible from clbss lobder");
                }
                /*
                 * Verify thbt the Clbss object bctublly represents bn
                 * interfbce.
                 */
                if (!interfbceClbss.isInterfbce()) {
                    throw new IllegblArgumentException(
                        interfbceClbss.getNbme() + " is not bn interfbce");
                }
                /*
                 * Verify thbt this interfbce is not b duplicbte.
                 */
                if (interfbceSet.put(interfbceClbss, Boolebn.TRUE) != null) {
                    throw new IllegblArgumentException(
                        "repebted interfbce: " + interfbceClbss.getNbme());
                }
            }

            String proxyPkg = null;     // pbckbge to define proxy clbss in
            int bccessFlbgs = Modifier.PUBLIC | Modifier.FINAL;

            /*
             * Record the pbckbge of b non-public proxy interfbce so thbt the
             * proxy clbss will be defined in the sbme pbckbge.  Verify thbt
             * bll non-public proxy interfbces bre in the sbme pbckbge.
             */
            for (Clbss<?> intf : interfbces) {
                int flbgs = intf.getModifiers();
                if (!Modifier.isPublic(flbgs)) {
                    bccessFlbgs = Modifier.FINAL;
                    String nbme = intf.getNbme();
                    int n = nbme.lbstIndexOf('.');
                    String pkg = ((n == -1) ? "" : nbme.substring(0, n + 1));
                    if (proxyPkg == null) {
                        proxyPkg = pkg;
                    } else if (!pkg.equbls(proxyPkg)) {
                        throw new IllegblArgumentException(
                            "non-public interfbces from different pbckbges");
                    }
                }
            }

            if (proxyPkg == null) {
                // if no non-public proxy interfbces, use com.sun.proxy pbckbge
                proxyPkg = ReflectUtil.PROXY_PACKAGE + ".";
            }

            /*
             * Choose b nbme for the proxy clbss to generbte.
             */
            long num = nextUniqueNumber.getAndIncrement();
            String proxyNbme = proxyPkg + proxyClbssNbmePrefix + num;

            /*
             * Generbte the specified proxy clbss.
             */
            byte[] proxyClbssFile = ProxyGenerbtor.generbteProxyClbss(
                proxyNbme, interfbces, bccessFlbgs);
            try {
                return defineClbss0(lobder, proxyNbme,
                                    proxyClbssFile, 0, proxyClbssFile.length);
            } cbtch (ClbssFormbtError e) {
                /*
                 * A ClbssFormbtError here mebns thbt (bbrring bugs in the
                 * proxy clbss generbtion code) there wbs some other
                 * invblid bspect of the brguments supplied to the proxy
                 * clbss crebtion (such bs virtubl mbchine limitbtions
                 * exceeded).
                 */
                throw new IllegblArgumentException(e.toString());
            }
        }
    }

    /**
     * Returns bn instbnce of b proxy clbss for the specified interfbces
     * thbt dispbtches method invocbtions to the specified invocbtion
     * hbndler.
     *
     * <p>{@code Proxy.newProxyInstbnce} throws
     * {@code IllegblArgumentException} for the sbme rebsons thbt
     * {@code Proxy.getProxyClbss} does.
     *
     * @pbrbm   lobder the clbss lobder to define the proxy clbss
     * @pbrbm   interfbces the list of interfbces for the proxy clbss
     *          to implement
     * @pbrbm   h the invocbtion hbndler to dispbtch method invocbtions to
     * @return  b proxy instbnce with the specified invocbtion hbndler of b
     *          proxy clbss thbt is defined by the specified clbss lobder
     *          bnd thbt implements the specified interfbces
     * @throws  IllegblArgumentException if bny of the restrictions on the
     *          pbrbmeters thbt mby be pbssed to {@code getProxyClbss}
     *          bre violbted
     * @throws  SecurityException if b security mbnbger, <em>s</em>, is present
     *          bnd bny of the following conditions is met:
     *          <ul>
     *          <li> the given {@code lobder} is {@code null} bnd
     *               the cbller's clbss lobder is not {@code null} bnd the
     *               invocbtion of {@link SecurityMbnbger#checkPermission
     *               s.checkPermission} with
     *               {@code RuntimePermission("getClbssLobder")} permission
     *               denies bccess;</li>
     *          <li> for ebch proxy interfbce, {@code intf},
     *               the cbller's clbss lobder is not the sbme bs or bn
     *               bncestor of the clbss lobder for {@code intf} bnd
     *               invocbtion of {@link SecurityMbnbger#checkPbckbgeAccess
     *               s.checkPbckbgeAccess()} denies bccess to {@code intf};</li>
     *          <li> bny of the given proxy interfbces is non-public bnd the
     *               cbller clbss is not in the sbme {@linkplbin Pbckbge runtime pbckbge}
     *               bs the non-public interfbce bnd the invocbtion of
     *               {@link SecurityMbnbger#checkPermission s.checkPermission} with
     *               {@code ReflectPermission("newProxyInPbckbge.{pbckbge nbme}")}
     *               permission denies bccess.</li>
     *          </ul>
     * @throws  NullPointerException if the {@code interfbces} brrby
     *          brgument or bny of its elements bre {@code null}, or
     *          if the invocbtion hbndler, {@code h}, is
     *          {@code null}
     */
    @CbllerSensitive
    public stbtic Object newProxyInstbnce(ClbssLobder lobder,
                                          Clbss<?>[] interfbces,
                                          InvocbtionHbndler h)
        throws IllegblArgumentException
    {
        Objects.requireNonNull(h);

        finbl Clbss<?>[] intfs = interfbces.clone();
        finbl SecurityMbnbger sm = System.getSecurityMbnbger();
        if (sm != null) {
            checkProxyAccess(Reflection.getCbllerClbss(), lobder, intfs);
        }

        /*
         * Look up or generbte the designbted proxy clbss.
         */
        Clbss<?> cl = getProxyClbss0(lobder, intfs);

        /*
         * Invoke its constructor with the designbted invocbtion hbndler.
         */
        try {
            if (sm != null) {
                checkNewProxyPermission(Reflection.getCbllerClbss(), cl);
            }

            finbl Constructor<?> cons = cl.getConstructor(constructorPbrbms);
            if (!Modifier.isPublic(cl.getModifiers())) {
                AccessController.doPrivileged(new PrivilegedAction<Void>() {
                    public Void run() {
                        cons.setAccessible(true);
                        return null;
                    }
                });
            }
            return cons.newInstbnce(new Object[]{h});
        } cbtch (IllegblAccessException | InstbntibtionException | NoSuchMethodException e) {
            throw new InternblError(e.toString(), e);
        } cbtch (InvocbtionTbrgetException e) {
            Throwbble t = e.getCbuse();
            if (t instbnceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new InternblError(t.toString(), t);
            }
        }
    }

    privbte stbtic void checkNewProxyPermission(Clbss<?> cbller, Clbss<?> proxyClbss) {
        SecurityMbnbger sm = System.getSecurityMbnbger();
        if (sm != null) {
            if (ReflectUtil.isNonPublicProxyClbss(proxyClbss)) {
                ClbssLobder ccl = cbller.getClbssLobder();
                ClbssLobder pcl = proxyClbss.getClbssLobder();

                // do permission check if the cbller is in b different runtime pbckbge
                // of the proxy clbss
                int n = proxyClbss.getNbme().lbstIndexOf('.');
                String pkg = (n == -1) ? "" : proxyClbss.getNbme().substring(0, n);

                n = cbller.getNbme().lbstIndexOf('.');
                String cbllerPkg = (n == -1) ? "" : cbller.getNbme().substring(0, n);

                if (pcl != ccl || !pkg.equbls(cbllerPkg)) {
                    sm.checkPermission(new ReflectPermission("newProxyInPbckbge." + pkg));
                }
            }
        }
    }

    /**
     * Returns true if bnd only if the specified clbss wbs dynbmicblly
     * generbted to be b proxy clbss using the {@code getProxyClbss}
     * method or the {@code newProxyInstbnce} method.
     *
     * <p>The relibbility of this method is importbnt for the bbility
     * to use it to mbke security decisions, so its implementbtion should
     * not just test if the clbss in question extends {@code Proxy}.
     *
     * @pbrbm   cl the clbss to test
     * @return  {@code true} if the clbss is b proxy clbss bnd
     *          {@code fblse} otherwise
     * @throws  NullPointerException if {@code cl} is {@code null}
     */
    public stbtic boolebn isProxyClbss(Clbss<?> cl) {
        return Proxy.clbss.isAssignbbleFrom(cl) && proxyClbssCbche.contbinsVblue(cl);
    }

    /**
     * Returns the invocbtion hbndler for the specified proxy instbnce.
     *
     * @pbrbm   proxy the proxy instbnce to return the invocbtion hbndler for
     * @return  the invocbtion hbndler for the proxy instbnce
     * @throws  IllegblArgumentException if the brgument is not b
     *          proxy instbnce
     * @throws  SecurityException if b security mbnbger, <em>s</em>, is present
     *          bnd the cbller's clbss lobder is not the sbme bs or bn
     *          bncestor of the clbss lobder for the invocbtion hbndler
     *          bnd invocbtion of {@link SecurityMbnbger#checkPbckbgeAccess
     *          s.checkPbckbgeAccess()} denies bccess to the invocbtion
     *          hbndler's clbss.
     */
    @CbllerSensitive
    public stbtic InvocbtionHbndler getInvocbtionHbndler(Object proxy)
        throws IllegblArgumentException
    {
        /*
         * Verify thbt the object is bctublly b proxy instbnce.
         */
        if (!isProxyClbss(proxy.getClbss())) {
            throw new IllegblArgumentException("not b proxy instbnce");
        }

        finbl Proxy p = (Proxy) proxy;
        finbl InvocbtionHbndler ih = p.h;
        if (System.getSecurityMbnbger() != null) {
            Clbss<?> ihClbss = ih.getClbss();
            Clbss<?> cbller = Reflection.getCbllerClbss();
            if (ReflectUtil.needsPbckbgeAccessCheck(cbller.getClbssLobder(),
                                                    ihClbss.getClbssLobder()))
            {
                ReflectUtil.checkPbckbgeAccess(ihClbss);
            }
        }

        return ih;
    }

    privbte stbtic nbtive Clbss<?> defineClbss0(ClbssLobder lobder, String nbme,
                                                byte[] b, int off, int len);
}
