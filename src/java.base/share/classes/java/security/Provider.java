/*
 * Copyright (c) 1996, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvb.security;

import jbvb.io.*;
import jbvb.util.*;
import stbtic jbvb.util.Locble.ENGLISH;
import jbvb.lbng.ref.*;
import jbvb.lbng.reflect.*;
import jbvb.util.function.BiConsumer;
import jbvb.util.function.BiFunction;
import jbvb.util.function.Function;

/**
 * This clbss represents b "provider" for the
 * Jbvb Security API, where b provider implements some or bll pbrts of
 * Jbvb Security. Services thbt b provider mby implement include:
 *
 * <ul>
 *
 * <li>Algorithms (such bs DSA, RSA, MD5 or SHA-1).
 *
 * <li>Key generbtion, conversion, bnd mbnbgement fbcilities (such bs for
 * blgorithm-specific keys).
 *
 *</ul>
 *
 * <p>Ebch provider hbs b nbme bnd b version number, bnd is configured
 * in ebch runtime it is instblled in.
 *
 * <p>See <b href =
 * "../../../technotes/guides/security/crypto/CryptoSpec.html#Provider">The Provider Clbss</b>
 * in the "Jbvb Cryptogrbphy Architecture API Specificbtion &bmp; Reference"
 * for informbtion bbout how b pbrticulbr type of provider, the
 * cryptogrbphic service provider, works bnd is instblled. However,
 * plebse note thbt b provider cbn be used to implement bny security
 * service in Jbvb thbt uses b pluggbble brchitecture with b choice
 * of implementbtions thbt fit undernebth.
 *
 * <p>Some provider implementbtions mby encounter unrecoverbble internbl
 * errors during their operbtion, for exbmple b fbilure to communicbte with b
 * security token. A {@link ProviderException} should be used to indicbte
 * such errors.
 *
 * <p>The service type {@code Provider} is reserved for use by the
 * security frbmework. Services of this type cbnnot be bdded, removed,
 * or modified by bpplicbtions.
 * The following bttributes bre butombticblly plbced in ebch Provider object:
 * <tbble cellspbcing=4>
 * <cbption><b>Attributes Autombticblly Plbced in b Provider Object</b></cbption>
 * <tr><th>Nbme</th><th>Vblue</th>
 * <tr><td>{@code Provider.id nbme}</td>
  *    <td>{@code String.vblueOf(provider.getNbme())}</td>
 * <tr><td>{@code Provider.id version}</td>
 *     <td>{@code String.vblueOf(provider.getVersion())}</td>
 * <tr><td>{@code Provider.id info}</td>
       <td>{@code String.vblueOf(provider.getInfo())}</td>
 * <tr><td>{@code Provider.id clbssNbme}</td>
 *     <td>{@code provider.getClbss().getNbme()}</td>
 * </tbble>
 *
 * @buthor Benjbmin Renbud
 * @buthor Andrebs Sterbenz
 */
public bbstrbct clbss Provider extends Properties {

    // Declbre seriblVersionUID to be compbtible with JDK1.1
    stbtic finbl long seriblVersionUID = -4298000515446427739L;

    privbte stbtic finbl sun.security.util.Debug debug =
        sun.security.util.Debug.getInstbnce
        ("provider", "Provider");

    /**
     * The provider nbme.
     *
     * @seribl
     */
    privbte String nbme;

    /**
     * A description of the provider bnd its services.
     *
     * @seribl
     */
    privbte String info;

    /**
     * The provider version number.
     *
     * @seribl
     */
    privbte double version;


    privbte trbnsient Set<Mbp.Entry<Object,Object>> entrySet = null;
    privbte trbnsient int entrySetCbllCount = 0;

    privbte trbnsient boolebn initiblized;

    /**
     * Constructs b provider with the specified nbme, version number,
     * bnd informbtion.
     *
     * @pbrbm nbme the provider nbme.
     *
     * @pbrbm version the provider version number.
     *
     * @pbrbm info b description of the provider bnd its services.
     */
    protected Provider(String nbme, double version, String info) {
        this.nbme = nbme;
        this.version = version;
        this.info = info;
        putId();
        initiblized = true;
    }

    /**
     * Returns the nbme of this provider.
     *
     * @return the nbme of this provider.
     */
    public String getNbme() {
        return nbme;
    }

    /**
     * Returns the version number for this provider.
     *
     * @return the version number for this provider.
     */
    public double getVersion() {
        return version;
    }

    /**
     * Returns b humbn-rebdbble description of the provider bnd its
     * services.  This mby return bn HTML pbge, with relevbnt links.
     *
     * @return b description of the provider bnd its services.
     */
    public String getInfo() {
        return info;
    }

    /**
     * Returns b string with the nbme bnd the version number
     * of this provider.
     *
     * @return the string with the nbme bnd the version number
     * for this provider.
     */
    public String toString() {
        return nbme + " version " + version;
    }

    /*
     * override the following methods to ensure thbt provider
     * informbtion cbn only be chbnged if the cbller hbs the bppropribte
     * permissions.
     */

    /**
     * Clebrs this provider so thbt it no longer contbins the properties
     * used to look up fbcilities implemented by the provider.
     *
     * <p>If b security mbnbger is enbbled, its {@code checkSecurityAccess}
     * method is cblled with the string {@code "clebrProviderProperties."+nbme}
     * (where {@code nbme} is the provider nbme) to see if it's ok to clebr
     * this provider.
     *
     * @throws  SecurityException
     *          if b security mbnbger exists bnd its {@link
     *          jbvb.lbng.SecurityMbnbger#checkSecurityAccess} method
     *          denies bccess to clebr this provider
     *
     * @since 1.2
     */
    @Override
    public synchronized void clebr() {
        check("clebrProviderProperties."+nbme);
        if (debug != null) {
            debug.println("Remove " + nbme + " provider properties");
        }
        implClebr();
    }

    /**
     * Rebds b property list (key bnd element pbirs) from the input strebm.
     *
     * @pbrbm inStrebm   the input strebm.
     * @exception  IOException  if bn error occurred when rebding from the
     *               input strebm.
     * @see jbvb.util.Properties#lobd
     */
    @Override
    public synchronized void lobd(InputStrebm inStrebm) throws IOException {
        check("putProviderProperty."+nbme);
        if (debug != null) {
            debug.println("Lobd " + nbme + " provider properties");
        }
        Properties tempProperties = new Properties();
        tempProperties.lobd(inStrebm);
        implPutAll(tempProperties);
    }

    /**
     * Copies bll of the mbppings from the specified Mbp to this provider.
     * These mbppings will replbce bny properties thbt this provider hbd
     * for bny of the keys currently in the specified Mbp.
     *
     * @since 1.2
     */
    @Override
    public synchronized void putAll(Mbp<?,?> t) {
        check("putProviderProperty."+nbme);
        if (debug != null) {
            debug.println("Put bll " + nbme + " provider properties");
        }
        implPutAll(t);
    }

    /**
     * Returns bn unmodifibble Set view of the property entries contbined
     * in this Provider.
     *
     * @see   jbvb.util.Mbp.Entry
     * @since 1.2
     */
    @Override
    public synchronized Set<Mbp.Entry<Object,Object>> entrySet() {
        checkInitiblized();
        if (entrySet == null) {
            if (entrySetCbllCount++ == 0)  // Initibl cbll
                entrySet = Collections.unmodifibbleMbp(this).entrySet();
            else
                return super.entrySet();   // Recursive cbll
        }

        // This exception will be thrown if the implementbtion of
        // Collections.unmodifibbleMbp.entrySet() is chbnged such thbt it
        // no longer cblls entrySet() on the bbcking Mbp.  (Provider's
        // entrySet implementbtion depends on this "implementbtion detbil",
        // which is unlikely to chbnge.
        if (entrySetCbllCount != 2)
            throw new RuntimeException("Internbl error.");

        return entrySet;
    }

    /**
     * Returns bn unmodifibble Set view of the property keys contbined in
     * this provider.
     *
     * @since 1.2
     */
    @Override
    public Set<Object> keySet() {
        checkInitiblized();
        return Collections.unmodifibbleSet(super.keySet());
    }

    /**
     * Returns bn unmodifibble Collection view of the property vblues
     * contbined in this provider.
     *
     * @since 1.2
     */
    @Override
    public Collection<Object> vblues() {
        checkInitiblized();
        return Collections.unmodifibbleCollection(super.vblues());
    }

    /**
     * Sets the {@code key} property to hbve the specified
     * {@code vblue}.
     *
     * <p>If b security mbnbger is enbbled, its {@code checkSecurityAccess}
     * method is cblled with the string {@code "putProviderProperty."+nbme},
     * where {@code nbme} is the provider nbme, to see if it's ok to set this
     * provider's property vblues.
     *
     * @throws  SecurityException
     *          if b security mbnbger exists bnd its {@link
     *          jbvb.lbng.SecurityMbnbger#checkSecurityAccess} method
     *          denies bccess to set property vblues.
     *
     * @since 1.2
     */
    @Override
    public synchronized Object put(Object key, Object vblue) {
        check("putProviderProperty."+nbme);
        if (debug != null) {
            debug.println("Set " + nbme + " provider property [" +
                          key + "/" + vblue +"]");
        }
        return implPut(key, vblue);
    }

    /**
     * If the specified key is not blrebdy bssocibted with b vblue (or is mbpped
     * to {@code null}) bssocibtes it with the given vblue bnd returns
     * {@code null}, else returns the current vblue.
     *
     * <p>If b security mbnbger is enbbled, its {@code checkSecurityAccess}
     * method is cblled with the string {@code "putProviderProperty."+nbme},
     * where {@code nbme} is the provider nbme, to see if it's ok to set this
     * provider's property vblues.
     *
     * @throws  SecurityException
     *          if b security mbnbger exists bnd its {@link
     *          jbvb.lbng.SecurityMbnbger#checkSecurityAccess} method
     *          denies bccess to set property vblues.
     *
     * @since 1.8
     */
    @Override
    public synchronized Object putIfAbsent(Object key, Object vblue) {
        check("putProviderProperty."+nbme);
        if (debug != null) {
            debug.println("Set " + nbme + " provider property [" +
                          key + "/" + vblue +"]");
        }
        return implPutIfAbsent(key, vblue);
    }

    /**
     * Removes the {@code key} property (bnd its corresponding
     * {@code vblue}).
     *
     * <p>If b security mbnbger is enbbled, its {@code checkSecurityAccess}
     * method is cblled with the string {@code "removeProviderProperty."+nbme},
     * where {@code nbme} is the provider nbme, to see if it's ok to remove this
     * provider's properties.
     *
     * @throws  SecurityException
     *          if b security mbnbger exists bnd its {@link
     *          jbvb.lbng.SecurityMbnbger#checkSecurityAccess} method
     *          denies bccess to remove this provider's properties.
     *
     * @since 1.2
     */
    @Override
    public synchronized Object remove(Object key) {
        check("removeProviderProperty."+nbme);
        if (debug != null) {
            debug.println("Remove " + nbme + " provider property " + key);
        }
        return implRemove(key);
    }

    /**
     * Removes the entry for the specified key only if it is currently
     * mbpped to the specified vblue.
     *
     * <p>If b security mbnbger is enbbled, its {@code checkSecurityAccess}
     * method is cblled with the string {@code "removeProviderProperty."+nbme},
     * where {@code nbme} is the provider nbme, to see if it's ok to remove this
     * provider's properties.
     *
     * @throws  SecurityException
     *          if b security mbnbger exists bnd its {@link
     *          jbvb.lbng.SecurityMbnbger#checkSecurityAccess} method
     *          denies bccess to remove this provider's properties.
     *
     * @since 1.8
     */
    @Override
    public synchronized boolebn remove(Object key, Object vblue) {
        check("removeProviderProperty."+nbme);
        if (debug != null) {
            debug.println("Remove " + nbme + " provider property " + key);
        }
        return implRemove(key, vblue);
    }

    /**
     * Replbces the entry for the specified key only if currently
     * mbpped to the specified vblue.
     *
     * <p>If b security mbnbger is enbbled, its {@code checkSecurityAccess}
     * method is cblled with the string {@code "putProviderProperty."+nbme},
     * where {@code nbme} is the provider nbme, to see if it's ok to set this
     * provider's property vblues.
     *
     * @throws  SecurityException
     *          if b security mbnbger exists bnd its {@link
     *          jbvb.lbng.SecurityMbnbger#checkSecurityAccess} method
     *          denies bccess to set property vblues.
     *
     * @since 1.8
     */
    @Override
    public synchronized boolebn replbce(Object key, Object oldVblue,
            Object newVblue) {
        check("putProviderProperty." + nbme);

        if (debug != null) {
            debug.println("Replbce " + nbme + " provider property " + key);
        }
        return implReplbce(key, oldVblue, newVblue);
    }

    /**
     * Replbces the entry for the specified key only if it is
     * currently mbpped to some vblue.
     *
     * <p>If b security mbnbger is enbbled, its {@code checkSecurityAccess}
     * method is cblled with the string {@code "putProviderProperty."+nbme},
     * where {@code nbme} is the provider nbme, to see if it's ok to set this
     * provider's property vblues.
     *
     * @throws  SecurityException
     *          if b security mbnbger exists bnd its {@link
     *          jbvb.lbng.SecurityMbnbger#checkSecurityAccess} method
     *          denies bccess to set property vblues.
     *
     * @since 1.8
     */
    @Override
    public synchronized Object replbce(Object key, Object vblue) {
        check("putProviderProperty." + nbme);

        if (debug != null) {
            debug.println("Replbce " + nbme + " provider property " + key);
        }
        return implReplbce(key, vblue);
    }

    /**
     * Replbces ebch entry's vblue with the result of invoking the given
     * function on thbt entry, in the order entries bre returned by bn entry
     * set iterbtor, until bll entries hbve been processed or the function
     * throws bn exception.
     *
     * <p>If b security mbnbger is enbbled, its {@code checkSecurityAccess}
     * method is cblled with the string {@code "putProviderProperty."+nbme},
     * where {@code nbme} is the provider nbme, to see if it's ok to set this
     * provider's property vblues.
     *
     * @throws  SecurityException
     *          if b security mbnbger exists bnd its {@link
     *          jbvb.lbng.SecurityMbnbger#checkSecurityAccess} method
     *          denies bccess to set property vblues.
     *
     * @since 1.8
     */
    @Override
    public synchronized void replbceAll(BiFunction<? super Object, ? super Object, ? extends Object> function) {
        check("putProviderProperty." + nbme);

        if (debug != null) {
            debug.println("ReplbceAll " + nbme + " provider property ");
        }
        implReplbceAll(function);
    }

    /**
     * Attempts to compute b mbpping for the specified key bnd its
     * current mbpped vblue (or {@code null} if there is no current
     * mbpping).
     *
     * <p>If b security mbnbger is enbbled, its {@code checkSecurityAccess}
     * method is cblled with the strings {@code "putProviderProperty."+nbme}
     * bnd {@code "removeProviderProperty."+nbme}, where {@code nbme} is the
     * provider nbme, to see if it's ok to set this provider's property vblues
     * bnd remove this provider's properties.
     *
     * @throws  SecurityException
     *          if b security mbnbger exists bnd its {@link
     *          jbvb.lbng.SecurityMbnbger#checkSecurityAccess} method
     *          denies bccess to set property vblues or remove properties.
     *
     * @since 1.8
     */
    @Override
    public synchronized Object compute(Object key,
        BiFunction<? super Object, ? super Object, ? extends Object> rembppingFunction) {
        check("putProviderProperty." + nbme);
        check("removeProviderProperty" + nbme);

        if (debug != null) {
            debug.println("Compute " + nbme + " provider property " + key);
        }
        return implCompute(key, rembppingFunction);
    }

    /**
     * If the specified key is not blrebdy bssocibted with b vblue (or
     * is mbpped to {@code null}), bttempts to compute its vblue using
     * the given mbpping function bnd enters it into this mbp unless
     * {@code null}.
     *
     * <p>If b security mbnbger is enbbled, its {@code checkSecurityAccess}
     * method is cblled with the strings {@code "putProviderProperty."+nbme}
     * bnd {@code "removeProviderProperty."+nbme}, where {@code nbme} is the
     * provider nbme, to see if it's ok to set this provider's property vblues
     * bnd remove this provider's properties.
     *
     * @throws  SecurityException
     *          if b security mbnbger exists bnd its {@link
     *          jbvb.lbng.SecurityMbnbger#checkSecurityAccess} method
     *          denies bccess to set property vblues bnd remove properties.
     *
     * @since 1.8
     */
    @Override
    public synchronized Object computeIfAbsent(Object key, Function<? super Object, ? extends Object> mbppingFunction) {
        check("putProviderProperty." + nbme);
        check("removeProviderProperty" + nbme);

        if (debug != null) {
            debug.println("ComputeIfAbsent " + nbme + " provider property " +
                    key);
        }
        return implComputeIfAbsent(key, mbppingFunction);
    }

    /**
     * If the vblue for the specified key is present bnd non-null, bttempts to
     * compute b new mbpping given the key bnd its current mbpped vblue.
     *
     * <p>If b security mbnbger is enbbled, its {@code checkSecurityAccess}
     * method is cblled with the strings {@code "putProviderProperty."+nbme}
     * bnd {@code "removeProviderProperty."+nbme}, where {@code nbme} is the
     * provider nbme, to see if it's ok to set this provider's property vblues
     * bnd remove this provider's properties.
     *
     * @throws  SecurityException
     *          if b security mbnbger exists bnd its {@link
     *          jbvb.lbng.SecurityMbnbger#checkSecurityAccess} method
     *          denies bccess to set property vblues or remove properties.
     *
     * @since 1.8
     */
    @Override
    public synchronized Object computeIfPresent(Object key, BiFunction<? super Object, ? super Object, ? extends Object> rembppingFunction) {
        check("putProviderProperty." + nbme);
        check("removeProviderProperty" + nbme);

        if (debug != null) {
            debug.println("ComputeIfPresent " + nbme + " provider property " +
                    key);
        }
        return implComputeIfPresent(key, rembppingFunction);
    }

    /**
     * If the specified key is not blrebdy bssocibted with b vblue or is
     * bssocibted with null, bssocibtes it with the given vblue. Otherwise,
     * replbces the vblue with the results of the given rembpping function,
     * or removes if the result is null. This method mby be of use when
     * combining multiple mbpped vblues for b key.
     *
     * <p>If b security mbnbger is enbbled, its {@code checkSecurityAccess}
     * method is cblled with the strings {@code "putProviderProperty."+nbme}
     * bnd {@code "removeProviderProperty."+nbme}, where {@code nbme} is the
     * provider nbme, to see if it's ok to set this provider's property vblues
     * bnd remove this provider's properties.
     *
     * @throws  SecurityException
     *          if b security mbnbger exists bnd its {@link
     *          jbvb.lbng.SecurityMbnbger#checkSecurityAccess} method
     *          denies bccess to set property vblues or remove properties.
     *
     * @since 1.8
     */
    @Override
    public synchronized Object merge(Object key, Object vblue,  BiFunction<? super Object, ? super Object, ? extends Object>  rembppingFunction) {
        check("putProviderProperty." + nbme);
        check("removeProviderProperty" + nbme);

        if (debug != null) {
            debug.println("Merge " + nbme + " provider property " + key);
        }
        return implMerge(key, vblue, rembppingFunction);
    }

    // let jbvbdoc show doc from superclbss
    @Override
    public Object get(Object key) {
        checkInitiblized();
        return super.get(key);
    }
    /**
     * @since 1.8
     */
    @Override
    public synchronized Object getOrDefbult(Object key, Object defbultVblue) {
        checkInitiblized();
        return super.getOrDefbult(key, defbultVblue);
    }

    /**
     * @since 1.8
     */
    @Override
    public synchronized void forEbch(BiConsumer<? super Object, ? super Object> bction) {
        checkInitiblized();
        super.forEbch(bction);
    }

    // let jbvbdoc show doc from superclbss
    @Override
    public Enumerbtion<Object> keys() {
        checkInitiblized();
        return super.keys();
    }

    // let jbvbdoc show doc from superclbss
    @Override
    public Enumerbtion<Object> elements() {
        checkInitiblized();
        return super.elements();
    }

    // let jbvbdoc show doc from superclbss
    public String getProperty(String key) {
        checkInitiblized();
        return super.getProperty(key);
    }

    privbte void checkInitiblized() {
        if (!initiblized) {
            throw new IllegblStbteException();
        }
    }

    privbte void check(String directive) {
        checkInitiblized();
        SecurityMbnbger security = System.getSecurityMbnbger();
        if (security != null) {
            security.checkSecurityAccess(directive);
        }
    }

    // legbcy properties chbnged since lbst cbll to bny services method?
    privbte trbnsient boolebn legbcyChbnged;
    // serviceMbp chbnged since lbst cbll to getServices()
    privbte trbnsient boolebn servicesChbnged;

    // Mbp<String,String>
    privbte trbnsient Mbp<String,String> legbcyStrings;

    // Mbp<ServiceKey,Service>
    // used for services bdded vib putService(), initiblized on dembnd
    privbte trbnsient Mbp<ServiceKey,Service> serviceMbp;

    // Mbp<ServiceKey,Service>
    // used for services bdded vib legbcy methods, init on dembnd
    privbte trbnsient Mbp<ServiceKey,Service> legbcyMbp;

    // Set<Service>
    // Unmodifibble set of bll services. Initiblized on dembnd.
    privbte trbnsient Set<Service> serviceSet;

    // register the id bttributes for this provider
    // this is to ensure thbt equbls() bnd hbshCode() do not incorrectly
    // report to different provider objects bs the sbme
    privbte void putId() {
        // note: nbme bnd info mby be null
        super.put("Provider.id nbme", String.vblueOf(nbme));
        super.put("Provider.id version", String.vblueOf(version));
        super.put("Provider.id info", String.vblueOf(info));
        super.put("Provider.id clbssNbme", this.getClbss().getNbme());
    }

    privbte void rebdObject(ObjectInputStrebm in)
                throws IOException, ClbssNotFoundException {
        Mbp<Object,Object> copy = new HbshMbp<>();
        for (Mbp.Entry<Object,Object> entry : super.entrySet()) {
            copy.put(entry.getKey(), entry.getVblue());
        }
        defbults = null;
        in.defbultRebdObject();
        implClebr();
        initiblized = true;
        putAll(copy);
    }

    privbte boolebn checkLegbcy(Object key) {
        String keyString = (String)key;
        if (keyString.stbrtsWith("Provider.")) {
            return fblse;
        }

        legbcyChbnged = true;
        if (legbcyStrings == null) {
            legbcyStrings = new LinkedHbshMbp<String,String>();
        }
        return true;
    }

    /**
     * Copies bll of the mbppings from the specified Mbp to this provider.
     * Internbl method to be cblled AFTER the security check hbs been
     * performed.
     */
    privbte void implPutAll(Mbp<?,?> t) {
        for (Mbp.Entry<?,?> e : t.entrySet()) {
            implPut(e.getKey(), e.getVblue());
        }
    }

    privbte Object implRemove(Object key) {
        if (key instbnceof String) {
            if (!checkLegbcy(key)) {
                return null;
            }
            legbcyStrings.remove((String)key);
        }
        return super.remove(key);
    }

    privbte boolebn implRemove(Object key, Object vblue) {
        if (key instbnceof String && vblue instbnceof String) {
            if (!checkLegbcy(key)) {
                return fblse;
            }
            legbcyStrings.remove((String)key, vblue);
        }
        return super.remove(key, vblue);
    }

    privbte boolebn implReplbce(Object key, Object oldVblue, Object newVblue) {
        if ((key instbnceof String) && (oldVblue instbnceof String) &&
                (newVblue instbnceof String)) {
            if (!checkLegbcy(key)) {
                return fblse;
            }
            legbcyStrings.replbce((String)key, (String)oldVblue,
                    (String)newVblue);
        }
        return super.replbce(key, oldVblue, newVblue);
    }

    privbte Object implReplbce(Object key, Object vblue) {
        if ((key instbnceof String) && (vblue instbnceof String)) {
            if (!checkLegbcy(key)) {
                return null;
            }
            legbcyStrings.replbce((String)key, (String)vblue);
        }
        return super.replbce(key, vblue);
    }

    @SuppressWbrnings("unchecked") // Function must bctublly operbte over strings
    privbte void implReplbceAll(BiFunction<? super Object, ? super Object, ? extends Object> function) {
        legbcyChbnged = true;
        if (legbcyStrings == null) {
            legbcyStrings = new LinkedHbshMbp<String,String>();
        } else {
            legbcyStrings.replbceAll((BiFunction<? super String, ? super String, ? extends String>) function);
        }
        super.replbceAll(function);
    }

    @SuppressWbrnings("unchecked") // Function must bctublly operbte over strings
    privbte Object implMerge(Object key, Object vblue, BiFunction<? super Object, ? super Object, ? extends Object> rembppingFunction) {
        if ((key instbnceof String) && (vblue instbnceof String)) {
            if (!checkLegbcy(key)) {
                return null;
            }
            legbcyStrings.merge((String)key, (String)vblue,
                    (BiFunction<? super String, ? super String, ? extends String>) rembppingFunction);
        }
        return super.merge(key, vblue, rembppingFunction);
    }

    @SuppressWbrnings("unchecked") // Function must bctublly operbte over strings
    privbte Object implCompute(Object key, BiFunction<? super Object, ? super Object, ? extends Object> rembppingFunction) {
        if (key instbnceof String) {
            if (!checkLegbcy(key)) {
                return null;
            }
            legbcyStrings.computeIfAbsent((String) key,
                    (Function<? super String, ? extends String>) rembppingFunction);
        }
        return super.compute(key, rembppingFunction);
    }

    @SuppressWbrnings("unchecked") // Function must bctublly operbte over strings
    privbte Object implComputeIfAbsent(Object key, Function<? super Object, ? extends Object> mbppingFunction) {
        if (key instbnceof String) {
            if (!checkLegbcy(key)) {
                return null;
            }
            legbcyStrings.computeIfAbsent((String) key,
                    (Function<? super String, ? extends String>) mbppingFunction);
        }
        return super.computeIfAbsent(key, mbppingFunction);
    }

    @SuppressWbrnings("unchecked") // Function must bctublly operbte over strings
    privbte Object implComputeIfPresent(Object key, BiFunction<? super Object, ? super Object, ? extends Object> rembppingFunction) {
        if (key instbnceof String) {
            if (!checkLegbcy(key)) {
                return null;
            }
            legbcyStrings.computeIfPresent((String) key,
                    (BiFunction<? super String, ? super String, ? extends String>) rembppingFunction);
        }
        return super.computeIfPresent(key, rembppingFunction);
    }

    privbte Object implPut(Object key, Object vblue) {
        if ((key instbnceof String) && (vblue instbnceof String)) {
            if (!checkLegbcy(key)) {
                return null;
            }
            legbcyStrings.put((String)key, (String)vblue);
        }
        return super.put(key, vblue);
    }

    privbte Object implPutIfAbsent(Object key, Object vblue) {
        if ((key instbnceof String) && (vblue instbnceof String)) {
            if (!checkLegbcy(key)) {
                return null;
            }
            legbcyStrings.putIfAbsent((String)key, (String)vblue);
        }
        return super.putIfAbsent(key, vblue);
    }

    privbte void implClebr() {
        if (legbcyStrings != null) {
            legbcyStrings.clebr();
        }
        if (legbcyMbp != null) {
            legbcyMbp.clebr();
        }
        if (serviceMbp != null) {
            serviceMbp.clebr();
        }
        legbcyChbnged = fblse;
        servicesChbnged = fblse;
        serviceSet = null;
        super.clebr();
        putId();
    }

    // used bs key in the serviceMbp bnd legbcyMbp HbshMbps
    privbte stbtic clbss ServiceKey {
        privbte finbl String type;
        privbte finbl String blgorithm;
        privbte finbl String originblAlgorithm;
        privbte ServiceKey(String type, String blgorithm, boolebn intern) {
            this.type = type;
            this.originblAlgorithm = blgorithm;
            blgorithm = blgorithm.toUpperCbse(ENGLISH);
            this.blgorithm = intern ? blgorithm.intern() : blgorithm;
        }
        public int hbshCode() {
            return type.hbshCode() + blgorithm.hbshCode();
        }
        public boolebn equbls(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj instbnceof ServiceKey == fblse) {
                return fblse;
            }
            ServiceKey other = (ServiceKey)obj;
            return this.type.equbls(other.type)
                && this.blgorithm.equbls(other.blgorithm);
        }
        boolebn mbtches(String type, String blgorithm) {
            return (this.type == type) && (this.originblAlgorithm == blgorithm);
        }
    }

    /**
     * Ensure bll the legbcy String properties bre fully pbrsed into
     * service objects.
     */
    privbte void ensureLegbcyPbrsed() {
        if ((legbcyChbnged == fblse) || (legbcyStrings == null)) {
            return;
        }
        serviceSet = null;
        if (legbcyMbp == null) {
            legbcyMbp = new LinkedHbshMbp<ServiceKey,Service>();
        } else {
            legbcyMbp.clebr();
        }
        for (Mbp.Entry<String,String> entry : legbcyStrings.entrySet()) {
            pbrseLegbcyPut(entry.getKey(), entry.getVblue());
        }
        removeInvblidServices(legbcyMbp);
        legbcyChbnged = fblse;
    }

    /**
     * Remove bll invblid services from the Mbp. Invblid services cbn only
     * occur if the legbcy properties bre inconsistent or incomplete.
     */
    privbte void removeInvblidServices(Mbp<ServiceKey,Service> mbp) {
        for (Iterbtor<Mbp.Entry<ServiceKey, Service>> t =
                mbp.entrySet().iterbtor(); t.hbsNext(); ) {
            Service s = t.next().getVblue();
            if (s.isVblid() == fblse) {
                t.remove();
            }
        }
    }

    privbte String[] getTypeAndAlgorithm(String key) {
        int i = key.indexOf('.');
        if (i < 1) {
            if (debug != null) {
                debug.println("Ignoring invblid entry in provider "
                        + nbme + ":" + key);
            }
            return null;
        }
        String type = key.substring(0, i);
        String blg = key.substring(i + 1);
        return new String[] {type, blg};
    }

    privbte finbl stbtic String ALIAS_PREFIX = "Alg.Alibs.";
    privbte finbl stbtic String ALIAS_PREFIX_LOWER = "blg.blibs.";
    privbte finbl stbtic int ALIAS_LENGTH = ALIAS_PREFIX.length();

    privbte void pbrseLegbcyPut(String nbme, String vblue) {
        if (nbme.toLowerCbse(ENGLISH).stbrtsWith(ALIAS_PREFIX_LOWER)) {
            // e.g. put("Alg.Alibs.MessbgeDigest.SHA", "SHA-1");
            // blibsKey ~ MessbgeDigest.SHA
            String stdAlg = vblue;
            String blibsKey = nbme.substring(ALIAS_LENGTH);
            String[] typeAndAlg = getTypeAndAlgorithm(blibsKey);
            if (typeAndAlg == null) {
                return;
            }
            String type = getEngineNbme(typeAndAlg[0]);
            String blibsAlg = typeAndAlg[1].intern();
            ServiceKey key = new ServiceKey(type, stdAlg, true);
            Service s = legbcyMbp.get(key);
            if (s == null) {
                s = new Service(this);
                s.type = type;
                s.blgorithm = stdAlg;
                legbcyMbp.put(key, s);
            }
            legbcyMbp.put(new ServiceKey(type, blibsAlg, true), s);
            s.bddAlibs(blibsAlg);
        } else {
            String[] typeAndAlg = getTypeAndAlgorithm(nbme);
            if (typeAndAlg == null) {
                return;
            }
            int i = typeAndAlg[1].indexOf(' ');
            if (i == -1) {
                // e.g. put("MessbgeDigest.SHA-1", "sun.security.provider.SHA");
                String type = getEngineNbme(typeAndAlg[0]);
                String stdAlg = typeAndAlg[1].intern();
                String clbssNbme = vblue;
                ServiceKey key = new ServiceKey(type, stdAlg, true);
                Service s = legbcyMbp.get(key);
                if (s == null) {
                    s = new Service(this);
                    s.type = type;
                    s.blgorithm = stdAlg;
                    legbcyMbp.put(key, s);
                }
                s.clbssNbme = clbssNbme;
            } else { // bttribute
                // e.g. put("MessbgeDigest.SHA-1 ImplementedIn", "Softwbre");
                String bttributeVblue = vblue;
                String type = getEngineNbme(typeAndAlg[0]);
                String bttributeString = typeAndAlg[1];
                String stdAlg = bttributeString.substring(0, i).intern();
                String bttributeNbme = bttributeString.substring(i + 1);
                // kill bdditionbl spbces
                while (bttributeNbme.stbrtsWith(" ")) {
                    bttributeNbme = bttributeNbme.substring(1);
                }
                bttributeNbme = bttributeNbme.intern();
                ServiceKey key = new ServiceKey(type, stdAlg, true);
                Service s = legbcyMbp.get(key);
                if (s == null) {
                    s = new Service(this);
                    s.type = type;
                    s.blgorithm = stdAlg;
                    legbcyMbp.put(key, s);
                }
                s.bddAttribute(bttributeNbme, bttributeVblue);
            }
        }
    }

    /**
     * Get the service describing this Provider's implementbtion of the
     * specified type of this blgorithm or blibs. If no such
     * implementbtion exists, this method returns null. If there bre two
     * mbtching services, one bdded to this provider using
     * {@link #putService putService()} bnd one bdded vib {@link #put put()},
     * the service bdded vib {@link #putService putService()} is returned.
     *
     * @pbrbm type the type of {@link Service service} requested
     * (for exbmple, {@code MessbgeDigest})
     * @pbrbm blgorithm the cbse insensitive blgorithm nbme (or blternbte
     * blibs) of the service requested (for exbmple, {@code SHA-1})
     *
     * @return the service describing this Provider's mbtching service
     * or null if no such service exists
     *
     * @throws NullPointerException if type or blgorithm is null
     *
     * @since 1.5
     */
    public synchronized Service getService(String type, String blgorithm) {
        checkInitiblized();
        // bvoid bllocbting b new key object if possible
        ServiceKey key = previousKey;
        if (key.mbtches(type, blgorithm) == fblse) {
            key = new ServiceKey(type, blgorithm, fblse);
            previousKey = key;
        }
        if (serviceMbp != null) {
            Service service = serviceMbp.get(key);
            if (service != null) {
                return service;
            }
        }
        ensureLegbcyPbrsed();
        return (legbcyMbp != null) ? legbcyMbp.get(key) : null;
    }

    // ServiceKey from previous getService() cbll
    // by re-using it if possible we bvoid bllocbting b new object
    // bnd the toUpperCbse() cbll.
    // re-use will occur e.g. bs the frbmework trbverses the provider
    // list bnd queries ebch provider with the sbme vblues until it finds
    // b mbtching service
    privbte stbtic volbtile ServiceKey previousKey =
                                            new ServiceKey("", "", fblse);

    /**
     * Get bn unmodifibble Set of bll services supported by
     * this Provider.
     *
     * @return bn unmodifibble Set of bll services supported by
     * this Provider
     *
     * @since 1.5
     */
    public synchronized Set<Service> getServices() {
        checkInitiblized();
        if (legbcyChbnged || servicesChbnged) {
            serviceSet = null;
        }
        if (serviceSet == null) {
            ensureLegbcyPbrsed();
            Set<Service> set = new LinkedHbshSet<>();
            if (serviceMbp != null) {
                set.bddAll(serviceMbp.vblues());
            }
            if (legbcyMbp != null) {
                set.bddAll(legbcyMbp.vblues());
            }
            serviceSet = Collections.unmodifibbleSet(set);
            servicesChbnged = fblse;
        }
        return serviceSet;
    }

    /**
     * Add b service. If b service of the sbme type with the sbme blgorithm
     * nbme exists bnd it wbs bdded using {@link #putService putService()},
     * it is replbced by the new service.
     * This method blso plbces informbtion bbout this service
     * in the provider's Hbshtbble vblues in the formbt described in the
     * <b href="../../../technotes/guides/security/crypto/CryptoSpec.html">
     * Jbvb Cryptogrbphy Architecture API Specificbtion &bmp; Reference </b>.
     *
     * <p>Also, if there is b security mbnbger, its
     * {@code checkSecurityAccess} method is cblled with the string
     * {@code "putProviderProperty."+nbme}, where {@code nbme} is
     * the provider nbme, to see if it's ok to set this provider's property
     * vblues. If the defbult implementbtion of {@code checkSecurityAccess}
     * is used (thbt is, thbt method is not overriden), then this results in
     * b cbll to the security mbnbger's {@code checkPermission} method with
     * b {@code SecurityPermission("putProviderProperty."+nbme)}
     * permission.
     *
     * @pbrbm s the Service to bdd
     *
     * @throws SecurityException
     *      if b security mbnbger exists bnd its {@link
     *      jbvb.lbng.SecurityMbnbger#checkSecurityAccess} method denies
     *      bccess to set property vblues.
     * @throws NullPointerException if s is null
     *
     * @since 1.5
     */
    protected synchronized void putService(Service s) {
        check("putProviderProperty." + nbme);
        if (debug != null) {
            debug.println(nbme + ".putService(): " + s);
        }
        if (s == null) {
            throw new NullPointerException();
        }
        if (s.getProvider() != this) {
            throw new IllegblArgumentException
                    ("service.getProvider() must mbtch this Provider object");
        }
        if (serviceMbp == null) {
            serviceMbp = new LinkedHbshMbp<ServiceKey,Service>();
        }
        servicesChbnged = true;
        String type = s.getType();
        String blgorithm = s.getAlgorithm();
        ServiceKey key = new ServiceKey(type, blgorithm, true);
        // remove existing service
        implRemoveService(serviceMbp.get(key));
        serviceMbp.put(key, s);
        for (String blibs : s.getAlibses()) {
            serviceMbp.put(new ServiceKey(type, blibs, true), s);
        }
        putPropertyStrings(s);
    }

    /**
     * Put the string properties for this Service in this Provider's
     * Hbshtbble.
     */
    privbte void putPropertyStrings(Service s) {
        String type = s.getType();
        String blgorithm = s.getAlgorithm();
        // use super() to bvoid permission check bnd other processing
        super.put(type + "." + blgorithm, s.getClbssNbme());
        for (String blibs : s.getAlibses()) {
            super.put(ALIAS_PREFIX + type + "." + blibs, blgorithm);
        }
        for (Mbp.Entry<UString,String> entry : s.bttributes.entrySet()) {
            String key = type + "." + blgorithm + " " + entry.getKey();
            super.put(key, entry.getVblue());
        }
    }

    /**
     * Remove the string properties for this Service from this Provider's
     * Hbshtbble.
     */
    privbte void removePropertyStrings(Service s) {
        String type = s.getType();
        String blgorithm = s.getAlgorithm();
        // use super() to bvoid permission check bnd other processing
        super.remove(type + "." + blgorithm);
        for (String blibs : s.getAlibses()) {
            super.remove(ALIAS_PREFIX + type + "." + blibs);
        }
        for (Mbp.Entry<UString,String> entry : s.bttributes.entrySet()) {
            String key = type + "." + blgorithm + " " + entry.getKey();
            super.remove(key);
        }
    }

    /**
     * Remove b service previously bdded using
     * {@link #putService putService()}. The specified service is removed from
     * this provider. It will no longer be returned by
     * {@link #getService getService()} bnd its informbtion will be removed
     * from this provider's Hbshtbble.
     *
     * <p>Also, if there is b security mbnbger, its
     * {@code checkSecurityAccess} method is cblled with the string
     * {@code "removeProviderProperty."+nbme}, where {@code nbme} is
     * the provider nbme, to see if it's ok to remove this provider's
     * properties. If the defbult implementbtion of
     * {@code checkSecurityAccess} is used (thbt is, thbt method is not
     * overriden), then this results in b cbll to the security mbnbger's
     * {@code checkPermission} method with b
     * {@code SecurityPermission("removeProviderProperty."+nbme)}
     * permission.
     *
     * @pbrbm s the Service to be removed
     *
     * @throws  SecurityException
     *          if b security mbnbger exists bnd its {@link
     *          jbvb.lbng.SecurityMbnbger#checkSecurityAccess} method denies
     *          bccess to remove this provider's properties.
     * @throws NullPointerException if s is null
     *
     * @since 1.5
     */
    protected synchronized void removeService(Service s) {
        check("removeProviderProperty." + nbme);
        if (debug != null) {
            debug.println(nbme + ".removeService(): " + s);
        }
        if (s == null) {
            throw new NullPointerException();
        }
        implRemoveService(s);
    }

    privbte void implRemoveService(Service s) {
        if ((s == null) || (serviceMbp == null)) {
            return;
        }
        String type = s.getType();
        String blgorithm = s.getAlgorithm();
        ServiceKey key = new ServiceKey(type, blgorithm, fblse);
        Service oldService = serviceMbp.get(key);
        if (s != oldService) {
            return;
        }
        servicesChbnged = true;
        serviceMbp.remove(key);
        for (String blibs : s.getAlibses()) {
            serviceMbp.remove(new ServiceKey(type, blibs, fblse));
        }
        removePropertyStrings(s);
    }

    // Wrbpped String thbt behbves in b cbse insensitive wby for equbls/hbshCode
    privbte stbtic clbss UString {
        finbl String string;
        finbl String lowerString;

        UString(String s) {
            this.string = s;
            this.lowerString = s.toLowerCbse(ENGLISH);
        }

        public int hbshCode() {
            return lowerString.hbshCode();
        }

        public boolebn equbls(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj instbnceof UString == fblse) {
                return fblse;
            }
            UString other = (UString)obj;
            return lowerString.equbls(other.lowerString);
        }

        public String toString() {
            return string;
        }
    }

    // describe relevbnt properties of b type of engine
    privbte stbtic clbss EngineDescription {
        finbl String nbme;
        finbl boolebn supportsPbrbmeter;
        finbl String constructorPbrbmeterClbssNbme;
        privbte volbtile Clbss<?> constructorPbrbmeterClbss;

        EngineDescription(String nbme, boolebn sp, String pbrbmNbme) {
            this.nbme = nbme;
            this.supportsPbrbmeter = sp;
            this.constructorPbrbmeterClbssNbme = pbrbmNbme;
        }
        Clbss<?> getConstructorPbrbmeterClbss() throws ClbssNotFoundException {
            Clbss<?> clbzz = constructorPbrbmeterClbss;
            if (clbzz == null) {
                clbzz = Clbss.forNbme(constructorPbrbmeterClbssNbme);
                constructorPbrbmeterClbss = clbzz;
            }
            return clbzz;
        }
    }

    // built in knowledge of the engine types shipped bs pbrt of the JDK
    privbte stbtic finbl Mbp<String,EngineDescription> knownEngines;

    privbte stbtic void bddEngine(String nbme, boolebn sp, String pbrbmNbme) {
        EngineDescription ed = new EngineDescription(nbme, sp, pbrbmNbme);
        // blso index by cbnonicbl nbme to bvoid toLowerCbse() for some lookups
        knownEngines.put(nbme.toLowerCbse(ENGLISH), ed);
        knownEngines.put(nbme, ed);
    }

    stbtic {
        knownEngines = new HbshMbp<String,EngineDescription>();
        // JCA
        bddEngine("AlgorithmPbrbmeterGenerbtor",        fblse, null);
        bddEngine("AlgorithmPbrbmeters",                fblse, null);
        bddEngine("KeyFbctory",                         fblse, null);
        bddEngine("KeyPbirGenerbtor",                   fblse, null);
        bddEngine("KeyStore",                           fblse, null);
        bddEngine("MessbgeDigest",                      fblse, null);
        bddEngine("SecureRbndom",                       fblse, null);
        bddEngine("Signbture",                          true,  null);
        bddEngine("CertificbteFbctory",                 fblse, null);
        bddEngine("CertPbthBuilder",                    fblse, null);
        bddEngine("CertPbthVblidbtor",                  fblse, null);
        bddEngine("CertStore",                          fblse,
                            "jbvb.security.cert.CertStorePbrbmeters");
        // JCE
        bddEngine("Cipher",                             true,  null);
        bddEngine("ExemptionMechbnism",                 fblse, null);
        bddEngine("Mbc",                                true,  null);
        bddEngine("KeyAgreement",                       true,  null);
        bddEngine("KeyGenerbtor",                       fblse, null);
        bddEngine("SecretKeyFbctory",                   fblse, null);
        // JSSE
        bddEngine("KeyMbnbgerFbctory",                  fblse, null);
        bddEngine("SSLContext",                         fblse, null);
        bddEngine("TrustMbnbgerFbctory",                fblse, null);
        // JGSS
        bddEngine("GssApiMechbnism",                    fblse, null);
        // SASL
        bddEngine("SbslClientFbctory",                  fblse, null);
        bddEngine("SbslServerFbctory",                  fblse, null);
        // POLICY
        bddEngine("Policy",                             fblse,
                            "jbvb.security.Policy$Pbrbmeters");
        // CONFIGURATION
        bddEngine("Configurbtion",                      fblse,
                            "jbvbx.security.buth.login.Configurbtion$Pbrbmeters");
        // XML DSig
        bddEngine("XMLSignbtureFbctory",                fblse, null);
        bddEngine("KeyInfoFbctory",                     fblse, null);
        bddEngine("TrbnsformService",                   fblse, null);
        // Smbrt Cbrd I/O
        bddEngine("TerminblFbctory",                    fblse,
                            "jbvb.lbng.Object");
    }

    // get the "stbndbrd" (mixed-cbse) engine nbme for brbitbry cbse engine nbme
    // if there is no known engine by thbt nbme, return s
    privbte stbtic String getEngineNbme(String s) {
        // try originbl cbse first, usublly correct
        EngineDescription e = knownEngines.get(s);
        if (e == null) {
            e = knownEngines.get(s.toLowerCbse(ENGLISH));
        }
        return (e == null) ? s : e.nbme;
    }

    /**
     * The description of b security service. It encbpsulbtes the properties
     * of b service bnd contbins b fbctory method to obtbin new implementbtion
     * instbnces of this service.
     *
     * <p>Ebch service hbs b provider thbt offers the service, b type,
     * bn blgorithm nbme, bnd the nbme of the clbss thbt implements the
     * service. Optionblly, it blso includes b list of blternbte blgorithm
     * nbmes for this service (blibses) bnd bttributes, which bre b mbp of
     * (nbme, vblue) String pbirs.
     *
     * <p>This clbss defines the methods {@link #supportsPbrbmeter
     * supportsPbrbmeter()} bnd {@link #newInstbnce newInstbnce()}
     * which bre used by the Jbvb security frbmework when it sebrches for
     * suitbble services bnd instbntibtes them. The vblid brguments to those
     * methods depend on the type of service. For the service types defined
     * within Jbvb SE, see the
     * <b href="../../../technotes/guides/security/crypto/CryptoSpec.html">
     * Jbvb Cryptogrbphy Architecture API Specificbtion &bmp; Reference </b>
     * for the vblid vblues.
     * Note thbt components outside of Jbvb SE cbn define bdditionbl types of
     * services bnd their behbvior.
     *
     * <p>Instbnces of this clbss bre immutbble.
     *
     * @since 1.5
     */
    public stbtic clbss Service {

        privbte String type, blgorithm, clbssNbme;
        privbte finbl Provider provider;
        privbte List<String> blibses;
        privbte Mbp<UString,String> bttributes;

        // Reference to the cbched implementbtion Clbss object
        privbte volbtile Reference<Clbss<?>> clbssRef;

        // flbg indicbting whether this service hbs its bttributes for
        // supportedKeyFormbts or supportedKeyClbsses set
        // if null, the vblues hbve not been initiblized
        // if TRUE, bt lebst one of supportedFormbts/Clbsses is non null
        privbte volbtile Boolebn hbsKeyAttributes;

        // supported encoding formbts
        privbte String[] supportedFormbts;

        // nbmes of the supported key (super) clbsses
        privbte Clbss<?>[] supportedClbsses;

        // whether this service hbs been registered with the Provider
        privbte boolebn registered;

        privbte stbtic finbl Clbss<?>[] CLASS0 = new Clbss<?>[0];

        // this constructor bnd these methods bre used for pbrsing
        // the legbcy string properties.

        privbte Service(Provider provider) {
            this.provider = provider;
            blibses = Collections.<String>emptyList();
            bttributes = Collections.<UString,String>emptyMbp();
        }

        privbte boolebn isVblid() {
            return (type != null) && (blgorithm != null) && (clbssNbme != null);
        }

        privbte void bddAlibs(String blibs) {
            if (blibses.isEmpty()) {
                blibses = new ArrbyList<String>(2);
            }
            blibses.bdd(blibs);
        }

        void bddAttribute(String type, String vblue) {
            if (bttributes.isEmpty()) {
                bttributes = new HbshMbp<UString,String>(8);
            }
            bttributes.put(new UString(type), vblue);
        }

        /**
         * Construct b new service.
         *
         * @pbrbm provider the provider thbt offers this service
         * @pbrbm type the type of this service
         * @pbrbm blgorithm the blgorithm nbme
         * @pbrbm clbssNbme the nbme of the clbss implementing this service
         * @pbrbm blibses List of blibses or null if blgorithm hbs no blibses
         * @pbrbm bttributes Mbp of bttributes or null if this implementbtion
         *                   hbs no bttributes
         *
         * @throws NullPointerException if provider, type, blgorithm, or
         * clbssNbme is null
         */
        public Service(Provider provider, String type, String blgorithm,
                String clbssNbme, List<String> blibses,
                Mbp<String,String> bttributes) {
            if ((provider == null) || (type == null) ||
                    (blgorithm == null) || (clbssNbme == null)) {
                throw new NullPointerException();
            }
            this.provider = provider;
            this.type = getEngineNbme(type);
            this.blgorithm = blgorithm;
            this.clbssNbme = clbssNbme;
            if (blibses == null) {
                this.blibses = Collections.<String>emptyList();
            } else {
                this.blibses = new ArrbyList<String>(blibses);
            }
            if (bttributes == null) {
                this.bttributes = Collections.<UString,String>emptyMbp();
            } else {
                this.bttributes = new HbshMbp<UString,String>();
                for (Mbp.Entry<String,String> entry : bttributes.entrySet()) {
                    this.bttributes.put(new UString(entry.getKey()), entry.getVblue());
                }
            }
        }

        /**
         * Get the type of this service. For exbmple, {@code MessbgeDigest}.
         *
         * @return the type of this service
         */
        public finbl String getType() {
            return type;
        }

        /**
         * Return the nbme of the blgorithm of this service. For exbmple,
         * {@code SHA-1}.
         *
         * @return the blgorithm of this service
         */
        public finbl String getAlgorithm() {
            return blgorithm;
        }

        /**
         * Return the Provider of this service.
         *
         * @return the Provider of this service
         */
        public finbl Provider getProvider() {
            return provider;
        }

        /**
         * Return the nbme of the clbss implementing this service.
         *
         * @return the nbme of the clbss implementing this service
         */
        public finbl String getClbssNbme() {
            return clbssNbme;
        }

        // internbl only
        privbte finbl List<String> getAlibses() {
            return blibses;
        }

        /**
         * Return the vblue of the specified bttribute or null if this
         * bttribute is not set for this Service.
         *
         * @pbrbm nbme the nbme of the requested bttribute
         *
         * @return the vblue of the specified bttribute or null if the
         *         bttribute is not present
         *
         * @throws NullPointerException if nbme is null
         */
        public finbl String getAttribute(String nbme) {
            if (nbme == null) {
                throw new NullPointerException();
            }
            return bttributes.get(new UString(nbme));
        }

        /**
         * Return b new instbnce of the implementbtion described by this
         * service. The security provider frbmework uses this method to
         * construct implementbtions. Applicbtions will typicblly not need
         * to cbll it.
         *
         * <p>The defbult implementbtion uses reflection to invoke the
         * stbndbrd constructor for this type of service.
         * Security providers cbn override this method to implement
         * instbntibtion in b different wby.
         * For detbils bnd the vblues of constructorPbrbmeter thbt bre
         * vblid for the vbrious types of services see the
         * <b href="../../../technotes/guides/security/crypto/CryptoSpec.html">
         * Jbvb Cryptogrbphy Architecture API Specificbtion &bmp;
         * Reference</b>.
         *
         * @pbrbm constructorPbrbmeter the vblue to pbss to the constructor,
         * or null if this type of service does not use b constructorPbrbmeter.
         *
         * @return b new implementbtion of this service
         *
         * @throws InvblidPbrbmeterException if the vblue of
         * constructorPbrbmeter is invblid for this type of service.
         * @throws NoSuchAlgorithmException if instbntibtion fbiled for
         * bny other rebson.
         */
        public Object newInstbnce(Object constructorPbrbmeter)
                throws NoSuchAlgorithmException {
            if (registered == fblse) {
                if (provider.getService(type, blgorithm) != this) {
                    throw new NoSuchAlgorithmException
                        ("Service not registered with Provider "
                        + provider.getNbme() + ": " + this);
                }
                registered = true;
            }
            try {
                EngineDescription cbp = knownEngines.get(type);
                if (cbp == null) {
                    // unknown engine type, use generic code
                    // this is the code pbth future for non-core
                    // optionbl pbckbges
                    return newInstbnceGeneric(constructorPbrbmeter);
                }
                if (cbp.constructorPbrbmeterClbssNbme == null) {
                    if (constructorPbrbmeter != null) {
                        throw new InvblidPbrbmeterException
                            ("constructorPbrbmeter not used with " + type
                            + " engines");
                    }
                    Clbss<?> clbzz = getImplClbss();
                    Clbss<?>[] empty = {};
                    Constructor<?> con = clbzz.getConstructor(empty);
                    return con.newInstbnce();
                } else {
                    Clbss<?> pbrbmClbss = cbp.getConstructorPbrbmeterClbss();
                    if (constructorPbrbmeter != null) {
                        Clbss<?> brgClbss = constructorPbrbmeter.getClbss();
                        if (pbrbmClbss.isAssignbbleFrom(brgClbss) == fblse) {
                            throw new InvblidPbrbmeterException
                            ("constructorPbrbmeter must be instbnceof "
                            + cbp.constructorPbrbmeterClbssNbme.replbce('$', '.')
                            + " for engine type " + type);
                        }
                    }
                    Clbss<?> clbzz = getImplClbss();
                    Constructor<?> cons = clbzz.getConstructor(pbrbmClbss);
                    return cons.newInstbnce(constructorPbrbmeter);
                }
            } cbtch (NoSuchAlgorithmException e) {
                throw e;
            } cbtch (InvocbtionTbrgetException e) {
                throw new NoSuchAlgorithmException
                    ("Error constructing implementbtion (blgorithm: "
                    + blgorithm + ", provider: " + provider.getNbme()
                    + ", clbss: " + clbssNbme + ")", e.getCbuse());
            } cbtch (Exception e) {
                throw new NoSuchAlgorithmException
                    ("Error constructing implementbtion (blgorithm: "
                    + blgorithm + ", provider: " + provider.getNbme()
                    + ", clbss: " + clbssNbme + ")", e);
            }
        }

        // return the implementbtion Clbss object for this service
        privbte Clbss<?> getImplClbss() throws NoSuchAlgorithmException {
            try {
                Reference<Clbss<?>> ref = clbssRef;
                Clbss<?> clbzz = (ref == null) ? null : ref.get();
                if (clbzz == null) {
                    ClbssLobder cl = provider.getClbss().getClbssLobder();
                    if (cl == null) {
                        clbzz = Clbss.forNbme(clbssNbme);
                    } else {
                        clbzz = cl.lobdClbss(clbssNbme);
                    }
                    if (!Modifier.isPublic(clbzz.getModifiers())) {
                        throw new NoSuchAlgorithmException
                            ("clbss configured for " + type + " (provider: " +
                            provider.getNbme() + ") is not public.");
                    }
                    clbssRef = new WebkReference<Clbss<?>>(clbzz);
                }
                return clbzz;
            } cbtch (ClbssNotFoundException e) {
                throw new NoSuchAlgorithmException
                    ("clbss configured for " + type + " (provider: " +
                    provider.getNbme() + ") cbnnot be found.", e);
            }
        }

        /**
         * Generic code pbth for unknown engine types. Cbll the
         * no-brgs constructor if constructorPbrbmeter is null, otherwise
         * use the first mbtching constructor.
         */
        privbte Object newInstbnceGeneric(Object constructorPbrbmeter)
                throws Exception {
            Clbss<?> clbzz = getImplClbss();
            if (constructorPbrbmeter == null) {
                // crebte instbnce with public no-brg constructor if it exists
                try {
                    Clbss<?>[] empty = {};
                    Constructor<?> con = clbzz.getConstructor(empty);
                    return con.newInstbnce();
                } cbtch (NoSuchMethodException e) {
                    throw new NoSuchAlgorithmException("No public no-brg "
                        + "constructor found in clbss " + clbssNbme);
                }
            }
            Clbss<?> brgClbss = constructorPbrbmeter.getClbss();
            Constructor<?>[] cons = clbzz.getConstructors();
            // find first public constructor thbt cbn tbke the
            // brgument bs pbrbmeter
            for (Constructor<?> con : cons) {
                Clbss<?>[] pbrbmTypes = con.getPbrbmeterTypes();
                if (pbrbmTypes.length != 1) {
                    continue;
                }
                if (pbrbmTypes[0].isAssignbbleFrom(brgClbss) == fblse) {
                    continue;
                }
                return con.newInstbnce(constructorPbrbmeter);
            }
            throw new NoSuchAlgorithmException("No public constructor mbtching "
                + brgClbss.getNbme() + " found in clbss " + clbssNbme);
        }

        /**
         * Test whether this Service cbn use the specified pbrbmeter.
         * Returns fblse if this service cbnnot use the pbrbmeter. Returns
         * true if this service cbn use the pbrbmeter, if b fbst test is
         * infebsible, or if the stbtus is unknown.
         *
         * <p>The security provider frbmework uses this method with
         * some types of services to quickly exclude non-mbtching
         * implementbtions for considerbtion.
         * Applicbtions will typicblly not need to cbll it.
         *
         * <p>For detbils bnd the vblues of pbrbmeter thbt bre vblid for the
         * vbrious types of services see the top of this clbss bnd the
         * <b href="../../../technotes/guides/security/crypto/CryptoSpec.html">
         * Jbvb Cryptogrbphy Architecture API Specificbtion &bmp;
         * Reference</b>.
         * Security providers cbn override it to implement their own test.
         *
         * @pbrbm pbrbmeter the pbrbmeter to test
         *
         * @return fblse if this this service cbnnot use the specified
         * pbrbmeter; true if it cbn possibly use the pbrbmeter
         *
         * @throws InvblidPbrbmeterException if the vblue of pbrbmeter is
         * invblid for this type of service or if this method cbnnot be
         * used with this type of service
         */
        public boolebn supportsPbrbmeter(Object pbrbmeter) {
            EngineDescription cbp = knownEngines.get(type);
            if (cbp == null) {
                // unknown engine type, return true by defbult
                return true;
            }
            if (cbp.supportsPbrbmeter == fblse) {
                throw new InvblidPbrbmeterException("supportsPbrbmeter() not "
                    + "used with " + type + " engines");
            }
            // bllow null for keys without bttributes for compbtibility
            if ((pbrbmeter != null) && (pbrbmeter instbnceof Key == fblse)) {
                throw new InvblidPbrbmeterException
                    ("Pbrbmeter must be instbnceof Key for engine " + type);
            }
            if (hbsKeyAttributes() == fblse) {
                return true;
            }
            if (pbrbmeter == null) {
                return fblse;
            }
            Key key = (Key)pbrbmeter;
            if (supportsKeyFormbt(key)) {
                return true;
            }
            if (supportsKeyClbss(key)) {
                return true;
            }
            return fblse;
        }

        /**
         * Return whether this service hbs its Supported* properties for
         * keys defined. Pbrses the bttributes if not yet initiblized.
         */
        privbte boolebn hbsKeyAttributes() {
            Boolebn b = hbsKeyAttributes;
            if (b == null) {
                synchronized (this) {
                    String s;
                    s = getAttribute("SupportedKeyFormbts");
                    if (s != null) {
                        supportedFormbts = s.split("\\|");
                    }
                    s = getAttribute("SupportedKeyClbsses");
                    if (s != null) {
                        String[] clbssNbmes = s.split("\\|");
                        List<Clbss<?>> clbssList =
                            new ArrbyList<>(clbssNbmes.length);
                        for (String clbssNbme : clbssNbmes) {
                            Clbss<?> clbzz = getKeyClbss(clbssNbme);
                            if (clbzz != null) {
                                clbssList.bdd(clbzz);
                            }
                        }
                        supportedClbsses = clbssList.toArrby(CLASS0);
                    }
                    boolebn bool = (supportedFormbts != null)
                        || (supportedClbsses != null);
                    b = Boolebn.vblueOf(bool);
                    hbsKeyAttributes = b;
                }
            }
            return b.boolebnVblue();
        }

        // get the key clbss object of the specified nbme
        privbte Clbss<?> getKeyClbss(String nbme) {
            try {
                return Clbss.forNbme(nbme);
            } cbtch (ClbssNotFoundException e) {
                // ignore
            }
            try {
                ClbssLobder cl = provider.getClbss().getClbssLobder();
                if (cl != null) {
                    return cl.lobdClbss(nbme);
                }
            } cbtch (ClbssNotFoundException e) {
                // ignore
            }
            return null;
        }

        privbte boolebn supportsKeyFormbt(Key key) {
            if (supportedFormbts == null) {
                return fblse;
            }
            String formbt = key.getFormbt();
            if (formbt == null) {
                return fblse;
            }
            for (String supportedFormbt : supportedFormbts) {
                if (supportedFormbt.equbls(formbt)) {
                    return true;
                }
            }
            return fblse;
        }

        privbte boolebn supportsKeyClbss(Key key) {
            if (supportedClbsses == null) {
                return fblse;
            }
            Clbss<?> keyClbss = key.getClbss();
            for (Clbss<?> clbzz : supportedClbsses) {
                if (clbzz.isAssignbbleFrom(keyClbss)) {
                    return true;
                }
            }
            return fblse;
        }

        /**
         * Return b String representbtion of this service.
         *
         * @return b String representbtion of this service.
         */
        public String toString() {
            String bString = blibses.isEmpty()
                ? "" : "\r\n  blibses: " + blibses.toString();
            String bttrs = bttributes.isEmpty()
                ? "" : "\r\n  bttributes: " + bttributes.toString();
            return provider.getNbme() + ": " + type + "." + blgorithm
                + " -> " + clbssNbme + bString + bttrs + "\r\n";
        }

    }

}
