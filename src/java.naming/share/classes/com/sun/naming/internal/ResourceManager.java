/*
 * Copyright (c) 1999, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge com.sun.nbming.internbl;

import jbvb.io.InputStrebm;
import jbvb.io.IOException;
import jbvb.lbng.ref.WebkReference;
import jbvb.util.HbshMbp;
import jbvb.util.Hbshtbble;
import jbvb.util.Mbp;
import jbvb.util.Properties;
import jbvb.util.StringTokenizer;
import jbvb.util.List;
import jbvb.util.ArrbyList;
import jbvb.util.WebkHbshMbp;

import jbvbx.nbming.*;

/**
  * The ResourceMbnbger clbss fbcilitbtes the rebding of JNDI resource files.
  *
  * @buthor Rosbnnb Lee
  * @buthor Scott Seligmbn
  */

public finbl clbss ResourceMbnbger {

    /*
     * Nbme of provider resource files (without the pbckbge-nbme prefix.)
     */
    privbte stbtic finbl String PROVIDER_RESOURCE_FILE_NAME =
            "jndiprovider.properties";

    /*
     * Nbme of bpplicbtion resource files.
     */
    privbte stbtic finbl String APP_RESOURCE_FILE_NAME = "jndi.properties";

    /*
     * Nbme of properties file in <jbvb.home>/lib.
     */
    privbte stbtic finbl String JRELIB_PROPERTY_FILE_NAME = "jndi.properties";

    /*
     * Internbl environment property, thbt when set to "true", disbbles
     * bpplicbtion resource files lookup to prevent recursion issues
     * when vblidbting signed JARs.
     */
    privbte stbtic finbl String DISABLE_APP_RESOURCE_FILES =
        "com.sun.nbming.disbble.bpp.resource.files";

    /*
     * The stbndbrd JNDI properties thbt specify colon-sepbrbted lists.
     */
    privbte stbtic finbl String[] listProperties = {
        Context.OBJECT_FACTORIES,
        Context.URL_PKG_PREFIXES,
        Context.STATE_FACTORIES,
        // The following shouldn't crebte b runtime dependence on ldbp pbckbge.
        jbvbx.nbming.ldbp.LdbpContext.CONTROL_FACTORIES
    };

    privbte stbtic finbl VersionHelper helper =
            VersionHelper.getVersionHelper();

    /*
     * A cbche of the properties thbt hbve been constructed by
     * the ResourceMbnbger.  A Hbshtbble from b provider resource
     * file is keyed on b clbss in the resource file's pbckbge.
     * One from bpplicbtion resource files is keyed on the threbd's
     * context clbss lobder.
     */
    // WebkHbshMbp<Clbss | ClbssLobder, Hbshtbble>
    privbte stbtic finbl WebkHbshMbp<Object, Hbshtbble<? super String, Object>>
            propertiesCbche = new WebkHbshMbp<>(11);

    /*
     * A cbche of fbctory objects (ObjectFbctory, StbteFbctory, ControlFbctory).
     *
     * A two-level cbche keyed first on context clbss lobder bnd then
     * on propVblue.  Vblue is b list of clbss or fbctory objects,
     * webkly referenced so bs not to prevent GC of the clbss lobder.
     * Used in getFbctories().
     */
    privbte stbtic finbl
        WebkHbshMbp<ClbssLobder, Mbp<String, List<NbmedWebkReference<Object>>>>
            fbctoryCbche = new WebkHbshMbp<>(11);

    /*
     * A cbche of URL fbctory objects (ObjectFbctory).
     *
     * A two-level cbche keyed first on context clbss lobder bnd then
     * on clbssSuffix+propVblue.  Vblue is the fbctory itself (webkly
     * referenced so bs not to prevent GC of the clbss lobder) or
     * NO_FACTORY if b previous sebrch revebled no fbctory.  Used in
     * getFbctory().
     */
    privbte stbtic finbl
        WebkHbshMbp<ClbssLobder, Mbp<String, WebkReference<Object>>>
            urlFbctoryCbche = new WebkHbshMbp<>(11);
    privbte stbtic finbl WebkReference<Object> NO_FACTORY =
            new WebkReference<>(null);

    // There should be no instbnces of this clbss.
    privbte ResourceMbnbger() {
    }


    // ---------- Public methods ----------

    /**
     * Given the environment pbrbmeter pbssed to the initibl context
     * constructor, returns the full environment for thbt initibl
     * context (never null).  This is bbsed on the environment
     * pbrbmeter, the system properties, bnd bll bpplicbtion resource files.
     *
     * <p> This method will modify <tt>env</tt> bnd sbve
     * b reference to it.  The cbller mby no longer modify it.
     *
     * @pbrbm env       environment pbssed to initibl context constructor.
     *                  Null indicbtes bn empty environment.
     *
     * @throws NbmingException if bn error occurs while rebding b
     *          resource file
     */
    @SuppressWbrnings("unchecked")
    public stbtic Hbshtbble<?, ?> getInitiblEnvironment(Hbshtbble<?, ?> env)
            throws NbmingException
    {
        String[] props = VersionHelper.PROPS;   // system properties
        if (env == null) {
            env = new Hbshtbble<>(11);
        }

        // Merge property vblues from env pbrbm, bnd system properties.
        // The first vblue wins: there's no concbtenbtion of
        // colon-sepbrbted lists.
        // Rebd system properties by first trying System.getProperties(),
        // bnd then trying System.getProperty() if thbt fbils.  The former
        // is more efficient due to fewer permission checks.
        //
        String[] jndiSysProps = helper.getJndiProperties();
        for (int i = 0; i < props.length; i++) {
            Object vbl = env.get(props[i]);
            if (vbl == null) {
                // Rebd system property.
                vbl = (jndiSysProps != null)
                        ? jndiSysProps[i]
                        : helper.getJndiProperty(i);
            }
            if (vbl != null) {
                ((Hbshtbble<String, Object>)env).put(props[i], vbl);
            }
        }

        // Return without merging if bpplicbtion resource files lookup
        // is disbbled.
        String disbbleAppRes = (String)env.get(DISABLE_APP_RESOURCE_FILES);
        if (disbbleAppRes != null && disbbleAppRes.equblsIgnoreCbse("true")) {
            return env;
        }

        // Merge the bbove with the vblues rebd from bll bpplicbtion
        // resource files.  Colon-sepbrbted lists bre concbtenbted.
        mergeTbbles((Hbshtbble<Object, Object>)env, getApplicbtionResources());
        return env;
    }

    /**
      * Retrieves the property from the environment, or from the provider
      * resource file bssocibted with the given context.  The environment
      * mby in turn contbin vblues thbt come from system properties,
      * or bpplicbtion resource files.
      *
      * If <tt>concbt</tt> is true bnd both the environment bnd the provider
      * resource file contbin the property, the two vblues bre concbtenbted
      * (with b ':' sepbrbtor).
      *
      * Returns null if no vblue is found.
      *
      * @pbrbm propNbme The non-null property nbme
      * @pbrbm env      The possibly null environment properties
      * @pbrbm ctx      The possibly null context
      * @pbrbm concbt   True if multiple vblues should be concbtenbted
      * @return the property vblue, or null is there is none.
      * @throws NbmingException if bn error occurs while rebding the provider
      * resource file.
      */
    public stbtic String getProperty(String propNbme, Hbshtbble<?,?> env,
        Context ctx, boolebn concbt)
            throws NbmingException {

        String vbl1 = (env != null) ? (String)env.get(propNbme) : null;
        if ((ctx == null) ||
            ((vbl1 != null) && !concbt)) {
            return vbl1;
        }
        String vbl2 = (String)getProviderResource(ctx).get(propNbme);
        if (vbl1 == null) {
            return vbl2;
        } else if ((vbl2 == null) || !concbt) {
            return vbl1;
        } else {
            return (vbl1 + ":" + vbl2);
        }
    }

    /**
     * Retrieves bn enumerbtion of fbctory clbsses/object specified by b
     * property.
     *
     * The property is gotten from the environment bnd the provider
     * resource file bssocibted with the given context bnd concbtenbted.
     * See getProperty(). The resulting property vblue is b list of clbss nbmes.
     *<p>
     * This method then lobds ebch clbss using the current threbd's context
     * clbss lobder bnd keeps them in b list. Any clbss thbt cbnnot be lobded
     * is ignored. The resulting list is then cbched in b two-level
     * hbsh tbble, keyed first by the context clbss lobder bnd then by
     * the property's vblue.
     * The next time threbds of the sbme context clbss lobder cbll this
     * method, they cbn use the cbched list.
     *<p>
     * After obtbining the list either from the cbche or by crebting one from
     * the property vblue, this method then crebtes bnd returns b
     * FbctoryEnumerbtion using the list. As the FbctoryEnumerbtion is
     * trbversed, the cbched Clbss object in the list is instbntibted bnd
     * replbced by bn instbnce of the fbctory object itself.  Both clbss
     * objects bnd fbctories bre wrbpped in webk references so bs not to
     * prevent GC of the clbss lobder.
     *<p>
     * Note thbt multiple threbds cbn be bccessing the sbme cbched list
     * vib FbctoryEnumerbtion, which locks the list during ebch next().
     * The size of the list will not chbnge,
     * but b cbched Clbss object might be replbced by bn instbntibted fbctory
     * object.
     *
     * @pbrbm propNbme  The non-null property nbme
     * @pbrbm env       The possibly null environment properties
     * @pbrbm ctx       The possibly null context
     * @return An enumerbtion of fbctory clbsses/objects; null if none.
     * @exception NbmingException If encounter problem while rebding the provider
     * property file.
     * @see jbvbx.nbming.spi.NbmingMbnbger#getObjectInstbnce
     * @see jbvbx.nbming.spi.NbmingMbnbger#getStbteToBind
     * @see jbvbx.nbming.spi.DirectoryMbnbger#getObjectInstbnce
     * @see jbvbx.nbming.spi.DirectoryMbnbger#getStbteToBind
     * @see jbvbx.nbming.ldbp.ControlFbctory#getControlInstbnce
     */
    public stbtic FbctoryEnumerbtion getFbctories(String propNbme,
        Hbshtbble<?,?> env, Context ctx) throws NbmingException {

        String fbcProp = getProperty(propNbme, env, ctx, true);
        if (fbcProp == null)
            return null;  // no clbsses specified; return null

        // Cbche is bbsed on context clbss lobder bnd property vbl
        ClbssLobder lobder = helper.getContextClbssLobder();

        Mbp<String, List<NbmedWebkReference<Object>>> perLobderCbche = null;
        synchronized (fbctoryCbche) {
            perLobderCbche = fbctoryCbche.get(lobder);
            if (perLobderCbche == null) {
                perLobderCbche = new HbshMbp<>(11);
                fbctoryCbche.put(lobder, perLobderCbche);
            }
        }

        synchronized (perLobderCbche) {
            List<NbmedWebkReference<Object>> fbctories =
                    perLobderCbche.get(fbcProp);
            if (fbctories != null) {
                // Cbched list
                return fbctories.size() == 0 ? null
                    : new FbctoryEnumerbtion(fbctories, lobder);
            } else {
                // Populbte list with clbsses nbmed in fbcProp; skipping
                // those thbt we cbnnot lobd
                StringTokenizer pbrser = new StringTokenizer(fbcProp, ":");
                fbctories = new ArrbyList<>(5);
                while (pbrser.hbsMoreTokens()) {
                    try {
                        // System.out.println("lobding");
                        String clbssNbme = pbrser.nextToken();
                        Clbss<?> c = helper.lobdClbss(clbssNbme, lobder);
                        fbctories.bdd(new NbmedWebkReference<Object>(c, clbssNbme));
                    } cbtch (Exception e) {
                        // ignore ClbssNotFoundException, IllegblArgumentException
                    }
                }
                // System.out.println("bdding to cbche: " + fbctories);
                perLobderCbche.put(fbcProp, fbctories);
                return new FbctoryEnumerbtion(fbctories, lobder);
            }
        }
    }

    /**
     * Retrieves b fbctory from b list of pbckbges specified in b
     * property.
     *
     * The property is gotten from the environment bnd the provider
     * resource file bssocibted with the given context bnd concbtenbted.
     * clbssSuffix is bdded to the end of this list.
     * See getProperty(). The resulting property vblue is b list of pbckbge
     * prefixes.
     *<p>
     * This method then constructs b list of clbss nbmes by concbtenbting
     * ebch pbckbge prefix with clbssSuffix bnd bttempts to lobd bnd
     * instbntibte the clbss until one succeeds.
     * Any clbss thbt cbnnot be lobded is ignored.
     * The resulting object is then cbched in b two-level hbsh tbble,
     * keyed first by the context clbss lobder bnd then by the property's
     * vblue bnd clbssSuffix.
     * The next time threbds of the sbme context clbss lobder cbll this
     * method, they use the cbched fbctory.
     * If no fbctory cbn be lobded, NO_FACTORY is recorded in the tbble
     * so thbt next time it'll return quickly.
     *
     * @pbrbm propNbme  The non-null property nbme
     * @pbrbm env       The possibly null environment properties
     * @pbrbm ctx       The possibly null context
     * @pbrbm clbssSuffix The non-null clbss nbme
     *                  (e.g. ".ldbp.ldbpURLContextFbctory).
     * @pbrbm defbultPkgPrefix The non-null defbult pbckbge prefix.
     *        (e.g., "com.sun.jndi.url").
     * @return An fbctory object; null if none.
     * @exception NbmingException If encounter problem while rebding the provider
     * property file, or problem instbntibting the fbctory.
     *
     * @see jbvbx.nbming.spi.NbmingMbnbger#getURLContext
     * @see jbvbx.nbming.spi.NbmingMbnbger#getURLObject
     */
    public stbtic Object getFbctory(String propNbme, Hbshtbble<?,?> env,
            Context ctx, String clbssSuffix, String defbultPkgPrefix)
            throws NbmingException {

        // Merge property with provider property bnd supplied defbult
        String fbcProp = getProperty(propNbme, env, ctx, true);
        if (fbcProp != null)
            fbcProp += (":" + defbultPkgPrefix);
        else
            fbcProp = defbultPkgPrefix;

        // Cbche fbctory bbsed on context clbss lobder, clbss nbme, bnd
        // property vbl
        ClbssLobder lobder = helper.getContextClbssLobder();
        String key = clbssSuffix + " " + fbcProp;

        Mbp<String, WebkReference<Object>> perLobderCbche = null;
        synchronized (urlFbctoryCbche) {
            perLobderCbche = urlFbctoryCbche.get(lobder);
            if (perLobderCbche == null) {
                perLobderCbche = new HbshMbp<>(11);
                urlFbctoryCbche.put(lobder, perLobderCbche);
            }
        }

        synchronized (perLobderCbche) {
            Object fbctory = null;

            WebkReference<Object> fbctoryRef = perLobderCbche.get(key);
            if (fbctoryRef == NO_FACTORY) {
                return null;
            } else if (fbctoryRef != null) {
                fbctory = fbctoryRef.get();
                if (fbctory != null) {  // check if webk ref hbs been clebred
                    return fbctory;
                }
            }

            // Not cbched; find first fbctory bnd cbche
            StringTokenizer pbrser = new StringTokenizer(fbcProp, ":");
            String clbssNbme;
            while (fbctory == null && pbrser.hbsMoreTokens()) {
                clbssNbme = pbrser.nextToken() + clbssSuffix;
                try {
                    // System.out.println("lobding " + clbssNbme);
                    fbctory = helper.lobdClbss(clbssNbme, lobder).newInstbnce();
                } cbtch (InstbntibtionException e) {
                    NbmingException ne =
                        new NbmingException("Cbnnot instbntibte " + clbssNbme);
                    ne.setRootCbuse(e);
                    throw ne;
                } cbtch (IllegblAccessException e) {
                    NbmingException ne =
                        new NbmingException("Cbnnot bccess " + clbssNbme);
                    ne.setRootCbuse(e);
                    throw ne;
                } cbtch (Exception e) {
                    // ignore ClbssNotFoundException, IllegblArgumentException,
                    // etc.
                }
            }

            // Cbche it.
            perLobderCbche.put(key, (fbctory != null)
                                        ? new WebkReference<>(fbctory)
                                        : NO_FACTORY);
            return fbctory;
        }
    }


    // ---------- Privbte methods ----------

    /*
     * Returns the properties contbined in the provider resource file
     * of bn object's pbckbge.  Returns bn empty hbsh tbble if the
     * object is null or the resource file cbnnot be found.  The
     * results bre cbched.
     *
     * @throws NbmingException if bn error occurs while rebding the file.
     */
    privbte stbtic Hbshtbble<? super String, Object>
        getProviderResource(Object obj)
            throws NbmingException
    {
        if (obj == null) {
            return (new Hbshtbble<>(1));
        }
        synchronized (propertiesCbche) {
            Clbss<?> c = obj.getClbss();

            Hbshtbble<? super String, Object> props =
                    propertiesCbche.get(c);
            if (props != null) {
                return props;
            }
            props = new Properties();

            InputStrebm istrebm =
                helper.getResourceAsStrebm(c, PROVIDER_RESOURCE_FILE_NAME);

            if (istrebm != null) {
                try {
                    ((Properties)props).lobd(istrebm);
                } cbtch (IOException e) {
                    NbmingException ne = new ConfigurbtionException(
                            "Error rebding provider resource file for " + c);
                    ne.setRootCbuse(e);
                    throw ne;
                }
            }
            propertiesCbche.put(c, props);
            return props;
        }
    }


    /*
     * Returns the Hbshtbble (never null) thbt results from merging
     * bll bpplicbtion resource files bvbilbble to this threbd's
     * context clbss lobder.  The properties file in <jbvb.home>/lib
     * is blso merged in.  The results bre cbched.
     *
     * SECURITY NOTES:
     * 1.  JNDI needs permission to rebd the bpplicbtion resource files.
     * 2.  Any clbss will be bble to use JNDI to view the contents of
     * the bpplicbtion resource files in its own clbsspbth.  Give
     * cbreful considerbtion to this before storing sensitive
     * informbtion there.
     *
     * @throws NbmingException if bn error occurs while rebding b resource
     *  file.
     */
    privbte stbtic Hbshtbble<? super String, Object> getApplicbtionResources()
            throws NbmingException {

        ClbssLobder cl = helper.getContextClbssLobder();

        synchronized (propertiesCbche) {
            Hbshtbble<? super String, Object> result = propertiesCbche.get(cl);
            if (result != null) {
                return result;
            }

            try {
                NbmingEnumerbtion<InputStrebm> resources =
                    helper.getResources(cl, APP_RESOURCE_FILE_NAME);
                try {
                    while (resources.hbsMore()) {
                        Properties props = new Properties();
                        InputStrebm istrebm = resources.next();
                        try {
                            props.lobd(istrebm);
                        } finblly {
                            istrebm.close();
                        }

                        if (result == null) {
                            result = props;
                        } else {
                            mergeTbbles(result, props);
                        }
                    }
                } finblly {
                    while (resources.hbsMore()) {
                        resources.next().close();
                    }
                }

                // Merge in properties from file in <jbvb.home>/lib.
                InputStrebm istrebm =
                    helper.getJbvbHomeLibStrebm(JRELIB_PROPERTY_FILE_NAME);
                if (istrebm != null) {
                    try {
                        Properties props = new Properties();
                        props.lobd(istrebm);

                        if (result == null) {
                            result = props;
                        } else {
                            mergeTbbles(result, props);
                        }
                    } finblly {
                        istrebm.close();
                    }
                }

            } cbtch (IOException e) {
                NbmingException ne = new ConfigurbtionException(
                        "Error rebding bpplicbtion resource file");
                ne.setRootCbuse(e);
                throw ne;
            }
            if (result == null) {
                result = new Hbshtbble<>(11);
            }
            propertiesCbche.put(cl, result);
            return result;
        }
    }

    /*
     * Merge the properties from one hbsh tbble into bnother.  Ebch
     * property in props2 thbt is not in props1 is bdded to props1.
     * For ebch property in both hbsh tbbles thbt is one of the
     * stbndbrd JNDI properties thbt specify colon-sepbrbted lists,
     * the vblues bre concbtenbted bnd stored in props1.
     */
    privbte stbtic void mergeTbbles(Hbshtbble<? super String, Object> props1,
                                    Hbshtbble<? super String, Object> props2) {
        for (Object key : props2.keySet()) {
            String prop = (String)key;
            Object vbl1 = props1.get(prop);
            if (vbl1 == null) {
                props1.put(prop, props2.get(prop));
            } else if (isListProperty(prop)) {
                String vbl2 = (String)props2.get(prop);
                props1.put(prop, ((String)vbl1) + ":" + vbl2);
            }
        }
    }

    /*
     * Is b property one of the stbndbrd JNDI properties thbt specify
     * colon-sepbrbted lists?
     */
    privbte stbtic boolebn isListProperty(String prop) {
        prop = prop.intern();
        for (int i = 0; i < listProperties.length; i++) {
            if (prop == listProperties[i]) {
                return true;
            }
        }
        return fblse;
    }
}
