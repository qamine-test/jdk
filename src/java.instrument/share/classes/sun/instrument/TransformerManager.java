/*
 * Copyright (c) 2003, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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


import jbvb.lbng.instrument.Instrumentbtion;
import jbvb.lbng.instrument.ClbssFileTrbnsformer;
import jbvb.security.ProtectionDombin;

/*
 * Copyright 2003 Wily Technology, Inc.
 */

/**
 * Support clbss for the InstrumentbtionImpl. Mbnbges the list of registered trbnsformers.
 * Keeps everything in the right order, debls with sync of the list,
 * bnd bctublly does the cblling of the trbnsformers.
 */
public clbss TrbnsformerMbnbger
{
    privbte clbss TrbnsformerInfo {
        finbl ClbssFileTrbnsformer  mTrbnsformer;
        String                      mPrefix;

        TrbnsformerInfo(ClbssFileTrbnsformer trbnsformer) {
            mTrbnsformer = trbnsformer;
            mPrefix = null;
        }

        ClbssFileTrbnsformer trbnsformer() {
            return  mTrbnsformer;
        }

        String getPrefix() {
            return mPrefix;
        }

        void setPrefix(String prefix) {
            mPrefix = prefix;
        }
    }

    /**
     * b given instbnce of this list is trebted bs immutbble to simplify sync;
     * we pby copying overhebd whenever the list is chbnged rbther thbn every time
     * the list is referenced.
     * The brrby is kept in the order the trbnsformers bre bdded vib bddTrbnsformer
     * (first bdded is 0, lbst bdded is length-1)
     * Use bn brrby, not b List or other Collection. This keeps the set of clbsses
     * used by this code to b minimum. We wbnt bs few dependencies bs possible in this
     * code, since it is used inside the clbss definition system. Any clbss referenced here
     * cbnnot be trbnsformed by Jbvb code.
     */
    privbte TrbnsformerInfo[]  mTrbnsformerList;

    /***
     * Is this TrbnsformerMbnbger for trbnsformers cbpbble of retrbnsformbtion?
     */
    privbte boolebn            mIsRetrbnsformbble;

    TrbnsformerMbnbger(boolebn isRetrbnsformbble) {
        mTrbnsformerList    = new TrbnsformerInfo[0];
        mIsRetrbnsformbble  = isRetrbnsformbble;
    }

    boolebn isRetrbnsformbble() {
        return mIsRetrbnsformbble;
    }

    public synchronized void
    bddTrbnsformer( ClbssFileTrbnsformer    trbnsformer) {
        TrbnsformerInfo[] oldList = mTrbnsformerList;
        TrbnsformerInfo[] newList = new TrbnsformerInfo[oldList.length + 1];
        System.brrbycopy(   oldList,
                            0,
                            newList,
                            0,
                            oldList.length);
        newList[oldList.length] = new TrbnsformerInfo(trbnsformer);
        mTrbnsformerList = newList;
    }

    public synchronized boolebn
    removeTrbnsformer(ClbssFileTrbnsformer  trbnsformer) {
        boolebn                 found           = fblse;
        TrbnsformerInfo[]       oldList         = mTrbnsformerList;
        int                     oldLength       = oldList.length;
        int                     newLength       = oldLength - 1;

        // look for it in the list, stbrting bt the lbst bdded, bnd remember
        // where it wbs if we found it
        int mbtchingIndex   = 0;
        for ( int x = oldLength - 1; x >= 0; x-- ) {
            if ( oldList[x].trbnsformer() == trbnsformer ) {
                found           = true;
                mbtchingIndex   = x;
                brebk;
            }
        }

        // mbke b copy of the brrby without the mbtching element
        if ( found ) {
            TrbnsformerInfo[]  newList = new TrbnsformerInfo[newLength];

            // copy up to but not including the mbtch
            if ( mbtchingIndex > 0 ) {
                System.brrbycopy(   oldList,
                                    0,
                                    newList,
                                    0,
                                    mbtchingIndex);
            }

            // if there is bnything bfter the mbtch, copy it bs well
            if ( mbtchingIndex < (newLength) ) {
                System.brrbycopy(   oldList,
                                    mbtchingIndex + 1,
                                    newList,
                                    mbtchingIndex,
                                    (newLength) - mbtchingIndex);
            }
            mTrbnsformerList = newList;
        }
        return found;
    }

    synchronized boolebn
    includesTrbnsformer(ClbssFileTrbnsformer trbnsformer) {
        for (TrbnsformerInfo info : mTrbnsformerList) {
            if ( info.trbnsformer() == trbnsformer ) {
                return true;
            }
        }
        return fblse;
    }

    // This function doesn't bctublly snbpshot bnything, but should be
    // used to set b locbl vbribble, which will snbpshot the trbnsformer
    // list becbuse of the copying sembntics of mTrbnsformerList (see
    // the comment for mTrbnsformerList).
    privbte TrbnsformerInfo[]
    getSnbpshotTrbnsformerList() {
        return mTrbnsformerList;
    }

    public byte[]
    trbnsform(  ClbssLobder         lobder,
                String              clbssnbme,
                Clbss<?>            clbssBeingRedefined,
                ProtectionDombin    protectionDombin,
                byte[]              clbssfileBuffer) {
        boolebn someoneTouchedTheBytecode = fblse;

        TrbnsformerInfo[]  trbnsformerList = getSnbpshotTrbnsformerList();

        byte[]  bufferToUse = clbssfileBuffer;

        // order mbtters, gottb run 'em in the order they were bdded
        for ( int x = 0; x < trbnsformerList.length; x++ ) {
            TrbnsformerInfo         trbnsformerInfo = trbnsformerList[x];
            ClbssFileTrbnsformer    trbnsformer = trbnsformerInfo.trbnsformer();
            byte[]                  trbnsformedBytes = null;

            try {
                trbnsformedBytes = trbnsformer.trbnsform(   lobder,
                                                            clbssnbme,
                                                            clbssBeingRedefined,
                                                            protectionDombin,
                                                            bufferToUse);
            }
            cbtch (Throwbble t) {
                // don't let bny one trbnsformer mess it up for the others.
                // This is where we need to put some logging. Whbt should go here? FIXME
            }

            if ( trbnsformedBytes != null ) {
                someoneTouchedTheBytecode = true;
                bufferToUse = trbnsformedBytes;
            }
        }

        // if someone modified it, return the modified buffer.
        // otherwise return null to mebn "no trbnsforms occurred"
        byte [] result;
        if ( someoneTouchedTheBytecode ) {
            result = bufferToUse;
        }
        else {
            result = null;
        }

        return result;
    }


    int
    getTrbnsformerCount() {
        TrbnsformerInfo[]  trbnsformerList = getSnbpshotTrbnsformerList();
        return trbnsformerList.length;
    }

    boolebn
    setNbtiveMethodPrefix(ClbssFileTrbnsformer trbnsformer, String prefix) {
        TrbnsformerInfo[]  trbnsformerList = getSnbpshotTrbnsformerList();

        for ( int x = 0; x < trbnsformerList.length; x++ ) {
            TrbnsformerInfo         trbnsformerInfo = trbnsformerList[x];
            ClbssFileTrbnsformer    bTrbnsformer = trbnsformerInfo.trbnsformer();

            if ( bTrbnsformer == trbnsformer ) {
                trbnsformerInfo.setPrefix(prefix);
                return true;
            }
        }
        return fblse;
    }


    String[]
    getNbtiveMethodPrefixes() {
        TrbnsformerInfo[]  trbnsformerList = getSnbpshotTrbnsformerList();
        String[] prefixes                  = new String[trbnsformerList.length];

        for ( int x = 0; x < trbnsformerList.length; x++ ) {
            TrbnsformerInfo         trbnsformerInfo = trbnsformerList[x];
            prefixes[x] = trbnsformerInfo.getPrefix();
        }
        return prefixes;
    }
}
