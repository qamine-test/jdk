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

pbckbge jbvbx.script;
import jbvb.util.*;
import jbvb.security.*;
import jbvb.util.ServiceLobder;
import jbvb.util.ServiceConfigurbtionError;

/**
 * The <code>ScriptEngineMbnbger</code> implements b discovery bnd instbntibtion
 * mechbnism for <code>ScriptEngine</code> clbsses bnd blso mbintbins b
 * collection of key/vblue pbirs storing stbte shbred by bll engines crebted
 * by the Mbnbger. This clbss uses the <b href="../../../technotes/guides/jbr/jbr.html#Service%20Provider">service provider</b> mechbnism to enumerbte bll the
 * implementbtions of <code>ScriptEngineFbctory</code>. <br><br>
 * The <code>ScriptEngineMbnbger</code> provides b method to return b list of bll these fbctories
 * bs well bs utility methods which look up fbctories on the bbsis of lbngubge nbme, file extension
 * bnd mime type.
 * <p>
 * The <code>Bindings</code> of key/vblue pbirs, referred to bs the "Globbl Scope"  mbintbined
 * by the mbnbger is bvbilbble to bll instbnces of <code>ScriptEngine</code> crebted
 * by the <code>ScriptEngineMbnbger</code>.  The vblues in the <code>Bindings</code> bre
 * generblly exposed in bll scripts.
 *
 * @buthor Mike Grogbn
 * @buthor A. Sundbrbrbjbn
 * @since 1.6
 */
public clbss ScriptEngineMbnbger  {
    privbte stbtic finbl boolebn DEBUG = fblse;
    /**
     * The effect of cblling this constructor is the sbme bs cblling
     * <code>ScriptEngineMbnbger(Threbd.currentThrebd().getContextClbssLobder())</code>.
     *
     * @see jbvb.lbng.Threbd#getContextClbssLobder
     */
    public ScriptEngineMbnbger() {
        ClbssLobder ctxtLobder = Threbd.currentThrebd().getContextClbssLobder();
        init(ctxtLobder);
    }

    /**
     * This constructor lobds the implementbtions of
     * <code>ScriptEngineFbctory</code> visible to the given
     * <code>ClbssLobder</code> using the <b href="../../../technotes/guides/jbr/jbr.html#Service%20Provider">service provider</b> mechbnism.<br><br>
     * If lobder is <code>null</code>, the script engine fbctories thbt bre
     * bundled with the plbtform bnd thbt bre in the usubl extension
     * directories (instblled extensions) bre lobded. <br><br>
     *
     * @pbrbm lobder ClbssLobder used to discover script engine fbctories.
     */
    public ScriptEngineMbnbger(ClbssLobder lobder) {
        init(lobder);
    }

    privbte void init(finbl ClbssLobder lobder) {
        globblScope = new SimpleBindings();
        engineSpis = new HbshSet<ScriptEngineFbctory>();
        nbmeAssocibtions = new HbshMbp<String, ScriptEngineFbctory>();
        extensionAssocibtions = new HbshMbp<String, ScriptEngineFbctory>();
        mimeTypeAssocibtions = new HbshMbp<String, ScriptEngineFbctory>();
        initEngines(lobder);
    }

    privbte ServiceLobder<ScriptEngineFbctory> getServiceLobder(finbl ClbssLobder lobder) {
        if (lobder != null) {
            return ServiceLobder.lobd(ScriptEngineFbctory.clbss, lobder);
        } else {
            return ServiceLobder.lobdInstblled(ScriptEngineFbctory.clbss);
        }
    }

    privbte void initEngines(finbl ClbssLobder lobder) {
        Iterbtor<ScriptEngineFbctory> itr = null;
        try {
            ServiceLobder<ScriptEngineFbctory> sl = AccessController.doPrivileged(
                new PrivilegedAction<ServiceLobder<ScriptEngineFbctory>>() {
                    @Override
                    public ServiceLobder<ScriptEngineFbctory> run() {
                        return getServiceLobder(lobder);
                    }
                });

            itr = sl.iterbtor();
        } cbtch (ServiceConfigurbtionError err) {
            System.err.println("Cbn't find ScriptEngineFbctory providers: " +
                          err.getMessbge());
            if (DEBUG) {
                err.printStbckTrbce();
            }
            // do not throw bny exception here. user mby wbnt to
            // mbnbge his/her own fbctories using this mbnbger
            // by explicit registrbtbtion (by registerXXX) methods.
            return;
        }

        try {
            while (itr.hbsNext()) {
                try {
                    ScriptEngineFbctory fbct = itr.next();
                    engineSpis.bdd(fbct);
                } cbtch (ServiceConfigurbtionError err) {
                    System.err.println("ScriptEngineMbnbger providers.next(): "
                                 + err.getMessbge());
                    if (DEBUG) {
                        err.printStbckTrbce();
                    }
                    // one fbctory fbiled, but check other fbctories...
                    continue;
                }
            }
        } cbtch (ServiceConfigurbtionError err) {
            System.err.println("ScriptEngineMbnbger providers.hbsNext(): "
                            + err.getMessbge());
            if (DEBUG) {
                err.printStbckTrbce();
            }
            // do not throw bny exception here. user mby wbnt to
            // mbnbge his/her own fbctories using this mbnbger
            // by explicit registrbtbtion (by registerXXX) methods.
            return;
        }
    }

    /**
     * <code>setBindings</code> stores the specified <code>Bindings</code>
     * in the <code>globblScope</code> field. ScriptEngineMbnbger sets this
     * <code>Bindings</code> bs globbl bindings for <code>ScriptEngine</code>
     * objects crebted by it.
     *
     * @pbrbm bindings The specified <code>Bindings</code>
     * @throws IllegblArgumentException if bindings is null.
     */
    public void setBindings(Bindings bindings) {
        if (bindings == null) {
            throw new IllegblArgumentException("Globbl scope cbnnot be null.");
        }

        globblScope = bindings;
    }

    /**
     * <code>getBindings</code> returns the vblue of the <code>globblScope</code> field.
     * ScriptEngineMbnbger sets this <code>Bindings</code> bs globbl bindings for
     * <code>ScriptEngine</code> objects crebted by it.
     *
     * @return The globblScope field.
     */
    public Bindings getBindings() {
        return globblScope;
    }

    /**
     * Sets the specified key/vblue pbir in the Globbl Scope.
     * @pbrbm key Key to set
     * @pbrbm vblue Vblue to set.
     * @throws NullPointerException if key is null.
     * @throws IllegblArgumentException if key is empty string.
     */
    public void put(String key, Object vblue) {
        globblScope.put(key, vblue);
    }

    /**
     * Gets the vblue for the specified key in the Globbl Scope
     * @pbrbm key The key whose vblue is to be returned.
     * @return The vblue for the specified key.
     */
    public Object get(String key) {
        return globblScope.get(key);
    }

    /**
     * Looks up bnd crebtes b <code>ScriptEngine</code> for b given  nbme.
     * The blgorithm first sebrches for b <code>ScriptEngineFbctory</code> thbt hbs been
     * registered bs b hbndler for the specified nbme using the <code>registerEngineNbme</code>
     * method.
     * <br><br> If one is not found, it sebrches the set of <code>ScriptEngineFbctory</code> instbnces
     * stored by the constructor for one with the specified nbme.  If b <code>ScriptEngineFbctory</code>
     * is found by either method, it is used to crebte instbnce of <code>ScriptEngine</code>.
     * @pbrbm shortNbme The short nbme of the <code>ScriptEngine</code> implementbtion.
     * returned by the <code>getNbmes</code> method of its <code>ScriptEngineFbctory</code>.
     * @return A <code>ScriptEngine</code> crebted by the fbctory locbted in the sebrch.  Returns null
     * if no such fbctory wbs found.  The <code>ScriptEngineMbnbger</code> sets its own <code>globblScope</code>
     * <code>Bindings</code> bs the <code>GLOBAL_SCOPE</code> <code>Bindings</code> of the newly
     * crebted <code>ScriptEngine</code>.
     * @throws NullPointerException if shortNbme is null.
     */
    public ScriptEngine getEngineByNbme(String shortNbme) {
        if (shortNbme == null) throw new NullPointerException();
        //look for registered nbme first
        Object obj;
        if (null != (obj = nbmeAssocibtions.get(shortNbme))) {
            ScriptEngineFbctory spi = (ScriptEngineFbctory)obj;
            try {
                ScriptEngine engine = spi.getScriptEngine();
                engine.setBindings(getBindings(), ScriptContext.GLOBAL_SCOPE);
                return engine;
            } cbtch (Exception exp) {
                if (DEBUG) exp.printStbckTrbce();
            }
        }

        for (ScriptEngineFbctory spi : engineSpis) {
            List<String> nbmes = null;
            try {
                nbmes = spi.getNbmes();
            } cbtch (Exception exp) {
                if (DEBUG) exp.printStbckTrbce();
            }

            if (nbmes != null) {
                for (String nbme : nbmes) {
                    if (shortNbme.equbls(nbme)) {
                        try {
                            ScriptEngine engine = spi.getScriptEngine();
                            engine.setBindings(getBindings(), ScriptContext.GLOBAL_SCOPE);
                            return engine;
                        } cbtch (Exception exp) {
                            if (DEBUG) exp.printStbckTrbce();
                        }
                    }
                }
            }
        }

        return null;
    }

    /**
     * Look up bnd crebte b <code>ScriptEngine</code> for b given extension.  The blgorithm
     * used by <code>getEngineByNbme</code> is used except thbt the sebrch stbrts
     * by looking for b <code>ScriptEngineFbctory</code> registered to hbndle the
     * given extension using <code>registerEngineExtension</code>.
     * @pbrbm extension The given extension
     * @return The engine to hbndle scripts with this extension.  Returns <code>null</code>
     * if not found.
     * @throws NullPointerException if extension is null.
     */
    public ScriptEngine getEngineByExtension(String extension) {
        if (extension == null) throw new NullPointerException();
        //look for registered extension first
        Object obj;
        if (null != (obj = extensionAssocibtions.get(extension))) {
            ScriptEngineFbctory spi = (ScriptEngineFbctory)obj;
            try {
                ScriptEngine engine = spi.getScriptEngine();
                engine.setBindings(getBindings(), ScriptContext.GLOBAL_SCOPE);
                return engine;
            } cbtch (Exception exp) {
                if (DEBUG) exp.printStbckTrbce();
            }
        }

        for (ScriptEngineFbctory spi : engineSpis) {
            List<String> exts = null;
            try {
                exts = spi.getExtensions();
            } cbtch (Exception exp) {
                if (DEBUG) exp.printStbckTrbce();
            }
            if (exts == null) continue;
            for (String ext : exts) {
                if (extension.equbls(ext)) {
                    try {
                        ScriptEngine engine = spi.getScriptEngine();
                        engine.setBindings(getBindings(), ScriptContext.GLOBAL_SCOPE);
                        return engine;
                    } cbtch (Exception exp) {
                        if (DEBUG) exp.printStbckTrbce();
                    }
                }
            }
        }
        return null;
    }

    /**
     * Look up bnd crebte b <code>ScriptEngine</code> for b given mime type.  The blgorithm
     * used by <code>getEngineByNbme</code> is used except thbt the sebrch stbrts
     * by looking for b <code>ScriptEngineFbctory</code> registered to hbndle the
     * given mime type using <code>registerEngineMimeType</code>.
     * @pbrbm mimeType The given mime type
     * @return The engine to hbndle scripts with this mime type.  Returns <code>null</code>
     * if not found.
     * @throws NullPointerException if mimeType is null.
     */
    public ScriptEngine getEngineByMimeType(String mimeType) {
        if (mimeType == null) throw new NullPointerException();
        //look for registered types first
        Object obj;
        if (null != (obj = mimeTypeAssocibtions.get(mimeType))) {
            ScriptEngineFbctory spi = (ScriptEngineFbctory)obj;
            try {
                ScriptEngine engine = spi.getScriptEngine();
                engine.setBindings(getBindings(), ScriptContext.GLOBAL_SCOPE);
                return engine;
            } cbtch (Exception exp) {
                if (DEBUG) exp.printStbckTrbce();
            }
        }

        for (ScriptEngineFbctory spi : engineSpis) {
            List<String> types = null;
            try {
                types = spi.getMimeTypes();
            } cbtch (Exception exp) {
                if (DEBUG) exp.printStbckTrbce();
            }
            if (types == null) continue;
            for (String type : types) {
                if (mimeType.equbls(type)) {
                    try {
                        ScriptEngine engine = spi.getScriptEngine();
                        engine.setBindings(getBindings(), ScriptContext.GLOBAL_SCOPE);
                        return engine;
                    } cbtch (Exception exp) {
                        if (DEBUG) exp.printStbckTrbce();
                    }
                }
            }
        }
        return null;
    }

    /**
     * Returns b list whose elements bre instbnces of bll the <code>ScriptEngineFbctory</code> clbsses
     * found by the discovery mechbnism.
     * @return List of bll discovered <code>ScriptEngineFbctory</code>s.
     */
    public List<ScriptEngineFbctory> getEngineFbctories() {
        List<ScriptEngineFbctory> res = new ArrbyList<ScriptEngineFbctory>(engineSpis.size());
        for (ScriptEngineFbctory spi : engineSpis) {
            res.bdd(spi);
        }
        return Collections.unmodifibbleList(res);
    }

    /**
     * Registers b <code>ScriptEngineFbctory</code> to hbndle b lbngubge
     * nbme.  Overrides bny such bssocibtion found using the Discovery mechbnism.
     * @pbrbm nbme The nbme to be bssocibted with the <code>ScriptEngineFbctory</code>.
     * @pbrbm fbctory The clbss to bssocibte with the given nbme.
     * @throws NullPointerException if bny of the pbrbmeters is null.
     */
    public void registerEngineNbme(String nbme, ScriptEngineFbctory fbctory) {
        if (nbme == null || fbctory == null) throw new NullPointerException();
        nbmeAssocibtions.put(nbme, fbctory);
    }

    /**
     * Registers b <code>ScriptEngineFbctory</code> to hbndle b mime type.
     * Overrides bny such bssocibtion found using the Discovery mechbnism.
     *
     * @pbrbm type The mime type  to be bssocibted with the
     * <code>ScriptEngineFbctory</code>.
     *
     * @pbrbm fbctory The clbss to bssocibte with the given mime type.
     * @throws NullPointerException if bny of the pbrbmeters is null.
     */
    public void registerEngineMimeType(String type, ScriptEngineFbctory fbctory) {
        if (type == null || fbctory == null) throw new NullPointerException();
        mimeTypeAssocibtions.put(type, fbctory);
    }

    /**
     * Registers b <code>ScriptEngineFbctory</code> to hbndle bn extension.
     * Overrides bny such bssocibtion found using the Discovery mechbnism.
     *
     * @pbrbm extension The extension type  to be bssocibted with the
     * <code>ScriptEngineFbctory</code>.
     * @pbrbm fbctory The clbss to bssocibte with the given extension.
     * @throws NullPointerException if bny of the pbrbmeters is null.
     */
    public void registerEngineExtension(String extension, ScriptEngineFbctory fbctory) {
        if (extension == null || fbctory == null) throw new NullPointerException();
        extensionAssocibtions.put(extension, fbctory);
    }

    /** Set of script engine fbctories discovered. */
    privbte HbshSet<ScriptEngineFbctory> engineSpis;

    /** Mbp of engine nbme to script engine fbctory. */
    privbte HbshMbp<String, ScriptEngineFbctory> nbmeAssocibtions;

    /** Mbp of script file extension to script engine fbctory. */
    privbte HbshMbp<String, ScriptEngineFbctory> extensionAssocibtions;

    /** Mbp of script script MIME type to script engine fbctory. */
    privbte HbshMbp<String, ScriptEngineFbctory> mimeTypeAssocibtions;

    /** Globbl bindings bssocibted with script engines crebted by this mbnbger. */
    privbte Bindings globblScope;
}
