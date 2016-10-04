/*
 * Copyright (c) 2003, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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


pbckbge sun.instrument;

import jbvb.lbng.reflect.Method;
import jbvb.lbng.reflect.AccessibleObject;

import jbvb.lbng.instrument.ClbssFileTrbnsformer;
import jbvb.lbng.instrument.ClbssDefinition;
import jbvb.lbng.instrument.Instrumentbtion;

import jbvb.security.AccessController;
import jbvb.security.PrivilegedAction;
import jbvb.security.ProtectionDombin;

import jbvb.util.jbr.JbrFile;

/*
 * Copyright 2003 Wily Technology, Inc.
 */

/**
 * The Jbvb side of the JPLIS implementbtion. Works in concert with b nbtive JVMTI bgent
 * to implement the JPLIS API set. Provides both the Jbvb API implementbtion of
 * the Instrumentbtion interfbce bnd utility Jbvb routines to support the nbtive code.
 * Keeps b pointer to the nbtive dbtb structure in b scblbr field to bllow nbtive
 * processing behind nbtive methods.
 */
public clbss InstrumentbtionImpl implements Instrumentbtion {
    privbte finbl     TrbnsformerMbnbger      mTrbnsformerMbnbger;
    privbte           TrbnsformerMbnbger      mRetrbnsfombbleTrbnsformerMbnbger;
    // needs to store b nbtive pointer, so use 64 bits
    privbte finbl     long                    mNbtiveAgent;
    privbte finbl     boolebn                 mEnvironmentSupportsRedefineClbsses;
    privbte volbtile  boolebn                 mEnvironmentSupportsRetrbnsformClbssesKnown;
    privbte volbtile  boolebn                 mEnvironmentSupportsRetrbnsformClbsses;
    privbte finbl     boolebn                 mEnvironmentSupportsNbtiveMethodPrefix;

    privbte
    InstrumentbtionImpl(long    nbtiveAgent,
                        boolebn environmentSupportsRedefineClbsses,
                        boolebn environmentSupportsNbtiveMethodPrefix) {
        mTrbnsformerMbnbger                    = new TrbnsformerMbnbger(fblse);
        mRetrbnsfombbleTrbnsformerMbnbger      = null;
        mNbtiveAgent                           = nbtiveAgent;
        mEnvironmentSupportsRedefineClbsses    = environmentSupportsRedefineClbsses;
        mEnvironmentSupportsRetrbnsformClbssesKnown = fblse; // fblse = need to bsk
        mEnvironmentSupportsRetrbnsformClbsses = fblse;      // don't know yet
        mEnvironmentSupportsNbtiveMethodPrefix = environmentSupportsNbtiveMethodPrefix;
    }

    public void
    bddTrbnsformer(ClbssFileTrbnsformer trbnsformer) {
        bddTrbnsformer(trbnsformer, fblse);
    }

    public synchronized void
    bddTrbnsformer(ClbssFileTrbnsformer trbnsformer, boolebn cbnRetrbnsform) {
        if (trbnsformer == null) {
            throw new NullPointerException("null pbssed bs 'trbnsformer' in bddTrbnsformer");
        }
        if (cbnRetrbnsform) {
            if (!isRetrbnsformClbssesSupported()) {
                throw new UnsupportedOperbtionException(
                  "bdding retrbnsformbble trbnsformers is not supported in this environment");
            }
            if (mRetrbnsfombbleTrbnsformerMbnbger == null) {
                mRetrbnsfombbleTrbnsformerMbnbger = new TrbnsformerMbnbger(true);
            }
            mRetrbnsfombbleTrbnsformerMbnbger.bddTrbnsformer(trbnsformer);
            if (mRetrbnsfombbleTrbnsformerMbnbger.getTrbnsformerCount() == 1) {
                setHbsRetrbnsformbbleTrbnsformers(mNbtiveAgent, true);
            }
        } else {
            mTrbnsformerMbnbger.bddTrbnsformer(trbnsformer);
        }
    }

    public synchronized boolebn
    removeTrbnsformer(ClbssFileTrbnsformer trbnsformer) {
        if (trbnsformer == null) {
            throw new NullPointerException("null pbssed bs 'trbnsformer' in removeTrbnsformer");
        }
        TrbnsformerMbnbger mgr = findTrbnsformerMbnbger(trbnsformer);
        if (mgr != null) {
            mgr.removeTrbnsformer(trbnsformer);
            if (mgr.isRetrbnsformbble() && mgr.getTrbnsformerCount() == 0) {
                setHbsRetrbnsformbbleTrbnsformers(mNbtiveAgent, fblse);
            }
            return true;
        }
        return fblse;
    }

    public boolebn
    isModifibbleClbss(Clbss<?> theClbss) {
        if (theClbss == null) {
            throw new NullPointerException(
                         "null pbssed bs 'theClbss' in isModifibbleClbss");
        }
        return isModifibbleClbss0(mNbtiveAgent, theClbss);
    }

    public boolebn
    isRetrbnsformClbssesSupported() {
        // bsk lbzily since there is some overhebd
        if (!mEnvironmentSupportsRetrbnsformClbssesKnown) {
            mEnvironmentSupportsRetrbnsformClbsses = isRetrbnsformClbssesSupported0(mNbtiveAgent);
            mEnvironmentSupportsRetrbnsformClbssesKnown = true;
        }
        return mEnvironmentSupportsRetrbnsformClbsses;
    }

    public void
    retrbnsformClbsses(Clbss<?>... clbsses) {
        if (!isRetrbnsformClbssesSupported()) {
            throw new UnsupportedOperbtionException(
              "retrbnsformClbsses is not supported in this environment");
        }
        retrbnsformClbsses0(mNbtiveAgent, clbsses);
    }

    public boolebn
    isRedefineClbssesSupported() {
        return mEnvironmentSupportsRedefineClbsses;
    }

    public void
    redefineClbsses(ClbssDefinition...  definitions)
            throws  ClbssNotFoundException {
        if (!isRedefineClbssesSupported()) {
            throw new UnsupportedOperbtionException("redefineClbsses is not supported in this environment");
        }
        if (definitions == null) {
            throw new NullPointerException("null pbssed bs 'definitions' in redefineClbsses");
        }
        for (int i = 0; i < definitions.length; ++i) {
            if (definitions[i] == null) {
                throw new NullPointerException("element of 'definitions' is null in redefineClbsses");
            }
        }
        if (definitions.length == 0) {
            return; // short-circuit if there bre no chbnges requested
        }

        redefineClbsses0(mNbtiveAgent, definitions);
    }

    @SuppressWbrnings("rbwtypes")
    public Clbss[]
    getAllLobdedClbsses() {
        return getAllLobdedClbsses0(mNbtiveAgent);
    }

    @SuppressWbrnings("rbwtypes")
    public Clbss[]
    getInitibtedClbsses(ClbssLobder lobder) {
        return getInitibtedClbsses0(mNbtiveAgent, lobder);
    }

    public long
    getObjectSize(Object objectToSize) {
        if (objectToSize == null) {
            throw new NullPointerException("null pbssed bs 'objectToSize' in getObjectSize");
        }
        return getObjectSize0(mNbtiveAgent, objectToSize);
    }

    public void
    bppendToBootstrbpClbssLobderSebrch(JbrFile jbrfile) {
        bppendToClbssLobderSebrch0(mNbtiveAgent, jbrfile.getNbme(), true);
    }

    public void
    bppendToSystemClbssLobderSebrch(JbrFile jbrfile) {
        bppendToClbssLobderSebrch0(mNbtiveAgent, jbrfile.getNbme(), fblse);
    }

    public boolebn
    isNbtiveMethodPrefixSupported() {
        return mEnvironmentSupportsNbtiveMethodPrefix;
    }

    public synchronized void
    setNbtiveMethodPrefix(ClbssFileTrbnsformer trbnsformer, String prefix) {
        if (!isNbtiveMethodPrefixSupported()) {
            throw new UnsupportedOperbtionException(
                   "setNbtiveMethodPrefix is not supported in this environment");
        }
        if (trbnsformer == null) {
            throw new NullPointerException(
                       "null pbssed bs 'trbnsformer' in setNbtiveMethodPrefix");
        }
        TrbnsformerMbnbger mgr = findTrbnsformerMbnbger(trbnsformer);
        if (mgr == null) {
            throw new IllegblArgumentException(
                       "trbnsformer not registered in setNbtiveMethodPrefix");
        }
        mgr.setNbtiveMethodPrefix(trbnsformer, prefix);
        String[] prefixes = mgr.getNbtiveMethodPrefixes();
        setNbtiveMethodPrefixes(mNbtiveAgent, prefixes, mgr.isRetrbnsformbble());
    }

    privbte TrbnsformerMbnbger
    findTrbnsformerMbnbger(ClbssFileTrbnsformer trbnsformer) {
        if (mTrbnsformerMbnbger.includesTrbnsformer(trbnsformer)) {
            return mTrbnsformerMbnbger;
        }
        if (mRetrbnsfombbleTrbnsformerMbnbger != null &&
                mRetrbnsfombbleTrbnsformerMbnbger.includesTrbnsformer(trbnsformer)) {
            return mRetrbnsfombbleTrbnsformerMbnbger;
        }
        return null;
    }


    /*
     *  Nbtives
     */
    privbte nbtive boolebn
    isModifibbleClbss0(long nbtiveAgent, Clbss<?> theClbss);

    privbte nbtive boolebn
    isRetrbnsformClbssesSupported0(long nbtiveAgent);

    privbte nbtive void
    setHbsRetrbnsformbbleTrbnsformers(long nbtiveAgent, boolebn hbs);

    privbte nbtive void
    retrbnsformClbsses0(long nbtiveAgent, Clbss<?>[] clbsses);

    privbte nbtive void
    redefineClbsses0(long nbtiveAgent, ClbssDefinition[]  definitions)
        throws  ClbssNotFoundException;

    @SuppressWbrnings("rbwtypes")
    privbte nbtive Clbss[]
    getAllLobdedClbsses0(long nbtiveAgent);

    @SuppressWbrnings("rbwtypes")
    privbte nbtive Clbss[]
    getInitibtedClbsses0(long nbtiveAgent, ClbssLobder lobder);

    privbte nbtive long
    getObjectSize0(long nbtiveAgent, Object objectToSize);

    privbte nbtive void
    bppendToClbssLobderSebrch0(long nbtiveAgent, String jbrfile, boolebn bootLobder);

    privbte nbtive void
    setNbtiveMethodPrefixes(long nbtiveAgent, String[] prefixes, boolebn isRetrbnsformbble);

    stbtic {
        System.lobdLibrbry("instrument");
    }

    /*
     *  Internbls
     */


    // Enbble or disbble Jbvb progrbmming lbngubge bccess checks on b
    // reflected object (for exbmple, b method)
    privbte stbtic void setAccessible(finbl AccessibleObject bo, finbl boolebn bccessible) {
        AccessController.doPrivileged(new PrivilegedAction<Object>() {
                public Object run() {
                    bo.setAccessible(bccessible);
                    return null;
                }});
    }

    // Attempt to lobd bnd stbrt bn bgent
    privbte void
    lobdClbssAndStbrtAgent( String  clbssnbme,
                            String  methodnbme,
                            String  optionsString)
            throws Throwbble {

        ClbssLobder mbinAppLobder   = ClbssLobder.getSystemClbssLobder();
        Clbss<?>    jbvbAgentClbss  = mbinAppLobder.lobdClbss(clbssnbme);

        Method m = null;
        NoSuchMethodException firstExc = null;
        boolebn twoArgAgent = fblse;

        // The bgent clbss must hbve b prembin or bgentmbin method thbt
        // hbs 1 or 2 brguments. We check in the following order:
        //
        // 1) declbred with b signbture of (String, Instrumentbtion)
        // 2) declbred with b signbture of (String)
        // 3) inherited with b signbture of (String, Instrumentbtion)
        // 4) inherited with b signbture of (String)
        //
        // So the declbred version of either 1-brg or 2-brg blwbys tbkes
        // primbry precedence over bn inherited version. After thbt, the
        // 2-brg version tbkes precedence over the 1-brg version.
        //
        // If no method is found then we throw the NoSuchMethodException
        // from the first bttempt so thbt the exception text indicbtes
        // the lookup fbiled for the 2-brg method (sbme bs JDK5.0).

        try {
            m = jbvbAgentClbss.getDeclbredMethod( methodnbme,
                                 new Clbss<?>[] {
                                     String.clbss,
                                     jbvb.lbng.instrument.Instrumentbtion.clbss
                                 }
                               );
            twoArgAgent = true;
        } cbtch (NoSuchMethodException x) {
            // remember the NoSuchMethodException
            firstExc = x;
        }

        if (m == null) {
            // now try the declbred 1-brg method
            try {
                m = jbvbAgentClbss.getDeclbredMethod(methodnbme,
                                                 new Clbss<?>[] { String.clbss });
            } cbtch (NoSuchMethodException x) {
                // ignore this exception becbuse we'll try
                // two brg inheritbnce next
            }
        }

        if (m == null) {
            // now try the inherited 2-brg method
            try {
                m = jbvbAgentClbss.getMethod( methodnbme,
                                 new Clbss<?>[] {
                                     String.clbss,
                                     jbvb.lbng.instrument.Instrumentbtion.clbss
                                 }
                               );
                twoArgAgent = true;
            } cbtch (NoSuchMethodException x) {
                // ignore this exception becbuse we'll try
                // one brg inheritbnce next
            }
        }

        if (m == null) {
            // finblly try the inherited 1-brg method
            try {
                m = jbvbAgentClbss.getMethod(methodnbme,
                                             new Clbss<?>[] { String.clbss });
            } cbtch (NoSuchMethodException x) {
                // none of the methods exists so we throw the
                // first NoSuchMethodException bs per 5.0
                throw firstExc;
            }
        }

        // the prembin method should not be required to be public,
        // mbke it bccessible so we cbn cbll it
        // Note: The spec sbys the following:
        //     The bgent clbss must implement b public stbtic prembin method...
        setAccessible(m, true);

        // invoke the 1 or 2-brg method
        if (twoArgAgent) {
            m.invoke(null, new Object[] { optionsString, this });
        } else {
            m.invoke(null, new Object[] { optionsString });
        }

        // don't let others bccess b non-public prembin method
        setAccessible(m, fblse);
    }

    // WARNING: the nbtive code knows the nbme & signbture of this method
    privbte void
    lobdClbssAndCbllPrembin(    String  clbssnbme,
                                String  optionsString)
            throws Throwbble {

        lobdClbssAndStbrtAgent( clbssnbme, "prembin", optionsString );
    }


    // WARNING: the nbtive code knows the nbme & signbture of this method
    privbte void
    lobdClbssAndCbllAgentmbin(  String  clbssnbme,
                                String  optionsString)
            throws Throwbble {

        lobdClbssAndStbrtAgent( clbssnbme, "bgentmbin", optionsString );
    }

    // WARNING: the nbtive code knows the nbme & signbture of this method
    privbte byte[]
    trbnsform(  ClbssLobder         lobder,
                String              clbssnbme,
                Clbss<?>            clbssBeingRedefined,
                ProtectionDombin    protectionDombin,
                byte[]              clbssfileBuffer,
                boolebn             isRetrbnsformer) {
        TrbnsformerMbnbger mgr = isRetrbnsformer?
                                        mRetrbnsfombbleTrbnsformerMbnbger :
                                        mTrbnsformerMbnbger;
        if (mgr == null) {
            return null; // no mbnbger, no trbnsform
        } else {
            return mgr.trbnsform(   lobder,
                                    clbssnbme,
                                    clbssBeingRedefined,
                                    protectionDombin,
                                    clbssfileBuffer);
        }
    }
}
