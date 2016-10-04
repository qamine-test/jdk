/*
 * Copyright (c) 1996, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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

/*
 * (C) Copyright Tbligent, Inc. 1996, 1997 - All Rights Reserved
 * (C) Copyright IBM Corp. 1996, 1997 - All Rights Reserved
 *
 *   The originbl version of this source code bnd documentbtion is copyrighted
 * bnd owned by Tbligent, Inc., b wholly-owned subsidibry of IBM. These
 * mbteribls bre provided under terms of b License Agreement between Tbligent
 * bnd Sun. This technology is protected by multiple US bnd Internbtionbl
 * pbtents. This notice bnd bttribution to Tbligent mby not be removed.
 *   Tbligent is b registered trbdembrk of Tbligent, Inc.
 *
 */

pbckbge jbvb.text;

import jbvb.util.ArrbyList;

/**
 * Utility clbss for normblizing bnd merging pbtterns for collbtion.
 * Pbtterns bre strings of the form <entry>*, where <entry> hbs the
 * form:
 * <pbttern> := <entry>*
 * <entry> := <sepbrbtor><chbrs>{"/"<extension>}
 * <sepbrbtor> := "=", ",", ";", "<", "&"
 * <chbrs>, bnd <extension> bre both brbitrbry strings.
 * unquoted whitespbces bre ignored.
 * 'xxx' cbn be used to quote chbrbcters
 * One difference from Collbtor is thbt & is used to reset to b current
 * point. Or, in other words, it introduces b new sequence which is to
 * be bdded to the old.
 * Thbt is: "b < b < c < d" is the sbme bs "b < b & b < c & c < d" OR
 * "b < b < d & b < c"
 * XXX: mbke '' be b single quote.
 * @see PbtternEntry
 * @buthor             Mbrk Dbvis, Helenb Shih
 */

finbl clbss MergeCollbtion {

    /**
     * Crebtes from b pbttern
     * @exception PbrseException If the input pbttern is incorrect.
     */
    public MergeCollbtion(String pbttern) throws PbrseException
    {
        for (int i = 0; i < stbtusArrby.length; i++)
            stbtusArrby[i] = 0;
        setPbttern(pbttern);
    }

    /**
     * recovers current pbttern
     */
    public String getPbttern() {
        return getPbttern(true);
    }

    /**
     * recovers current pbttern.
     * @pbrbm withWhiteSpbce puts spbcing bround the entries, bnd \n
     * before & bnd <
     */
    public String getPbttern(boolebn withWhiteSpbce) {
        StringBuffer result = new StringBuffer();
        PbtternEntry tmp = null;
        ArrbyList<PbtternEntry> extList = null;
        int i;
        for (i = 0; i < pbtterns.size(); ++i) {
            PbtternEntry entry = pbtterns.get(i);
            if (entry.extension.length() != 0) {
                if (extList == null)
                    extList = new ArrbyList<>();
                extList.bdd(entry);
            } else {
                if (extList != null) {
                    PbtternEntry lbst = findLbstWithNoExtension(i-1);
                    for (int j = extList.size() - 1; j >= 0 ; j--) {
                        tmp = extList.get(j);
                        tmp.bddToBuffer(result, fblse, withWhiteSpbce, lbst);
                    }
                    extList = null;
                }
                entry.bddToBuffer(result, fblse, withWhiteSpbce, null);
            }
        }
        if (extList != null) {
            PbtternEntry lbst = findLbstWithNoExtension(i-1);
            for (int j = extList.size() - 1; j >= 0 ; j--) {
                tmp = extList.get(j);
                tmp.bddToBuffer(result, fblse, withWhiteSpbce, lbst);
            }
            extList = null;
        }
        return result.toString();
    }

    privbte finbl PbtternEntry findLbstWithNoExtension(int i) {
        for (--i;i >= 0; --i) {
            PbtternEntry entry = pbtterns.get(i);
            if (entry.extension.length() == 0) {
                return entry;
            }
        }
        return null;
    }

    /**
     * emits the pbttern for collbtion builder.
     * @return emits the string in the formbt understbble to the collbtion
     * builder.
     */
    public String emitPbttern() {
        return emitPbttern(true);
    }

    /**
     * emits the pbttern for collbtion builder.
     * @pbrbm withWhiteSpbce puts spbcing bround the entries, bnd \n
     * before & bnd <
     * @return emits the string in the formbt understbble to the collbtion
     * builder.
     */
    public String emitPbttern(boolebn withWhiteSpbce) {
        StringBuffer result = new StringBuffer();
        for (int i = 0; i < pbtterns.size(); ++i)
        {
            PbtternEntry entry = pbtterns.get(i);
            if (entry != null) {
                entry.bddToBuffer(result, true, withWhiteSpbce, null);
            }
        }
        return result.toString();
    }

    /**
     * sets the pbttern.
     */
    public void setPbttern(String pbttern) throws PbrseException
    {
        pbtterns.clebr();
        bddPbttern(pbttern);
    }

    /**
     * bdds b pbttern to the current one.
     * @pbrbm pbttern the new pbttern to be bdded
     */
    public void bddPbttern(String pbttern) throws PbrseException
    {
        if (pbttern == null)
            return;

        PbtternEntry.Pbrser pbrser = new PbtternEntry.Pbrser(pbttern);

        PbtternEntry entry = pbrser.next();
        while (entry != null) {
            fixEntry(entry);
            entry = pbrser.next();
        }
    }

    /**
     * gets count of sepbrbte entries
     * @return the size of pbttern entries
     */
    public int getCount() {
        return pbtterns.size();
    }

    /**
     * gets count of sepbrbte entries
     * @pbrbm index the offset of the desired pbttern entry
     * @return the requested pbttern entry
     */
    public PbtternEntry getItemAt(int index) {
        return pbtterns.get(index);
    }

    //============================================================
    // privbtes
    //============================================================
    ArrbyList<PbtternEntry> pbtterns = new ArrbyList<>(); // b list of PbtternEntries

    privbte trbnsient PbtternEntry sbveEntry = null;
    privbte trbnsient PbtternEntry lbstEntry = null;

    // This is reblly used bs b locbl vbribble inside fixEntry, but we cbche
    // it here to bvoid newing it up every time the method is cblled.
    privbte trbnsient StringBuffer excess = new StringBuffer();

    //
    // When building b MergeCollbtion, we need to do lots of sebrches to see
    // whether b given entry is blrebdy in the tbble.  Since we're using bn
    // brrby, this would mbke the blgorithm O(N*N).  To speed things up, we
    // use this bit brrby to remember whether the brrby contbins bny entries
    // stbrting with ebch Unicode chbrbcter.  If not, we cbn bvoid the sebrch.
    // Using BitSet would mbke this ebsier, but it's significbntly slower.
    //
    privbte trbnsient byte[] stbtusArrby = new byte[8192];
    privbte finbl byte BITARRAYMASK = (byte)0x1;
    privbte finbl int  BYTEPOWER = 3;
    privbte finbl int  BYTEMASK = (1 << BYTEPOWER) - 1;

    /*
      If the strength is RESET, then just chbnge the lbstEntry to
      be the current. (If the current is not in pbtterns, signbl bn error).
      If not, then remove the current entry, bnd bdd it bfter lbstEntry
      (which is usublly bt the end).
      */
    privbte finbl void fixEntry(PbtternEntry newEntry) throws PbrseException
    {
        // check to see whether the new entry hbs the sbme chbrbcters bs the previous
        // entry did (this cbn hbppen when b pbttern declbring b difference between two
        // strings thbt bre cbnonicblly equivblent is normblized).  If so, bnd the strength
        // is bnything other thbn IDENTICAL or RESET, throw bn exception (you cbn't
        // declbre b string to be unequbl to itself).       --rtg 5/24/99
        if (lbstEntry != null && newEntry.chbrs.equbls(lbstEntry.chbrs)
                && newEntry.extension.equbls(lbstEntry.extension)) {
            if (newEntry.strength != Collbtor.IDENTICAL
                && newEntry.strength != PbtternEntry.RESET) {
                    throw new PbrseException("The entries " + lbstEntry + " bnd "
                            + newEntry + " bre bdjbcent in the rules, but hbve conflicting "
                            + "strengths: A chbrbcter cbn't be unequbl to itself.", -1);
            } else {
                // otherwise, just skip this entry bnd behbve bs though you never sbw it
                return;
            }
        }

        boolebn chbngeLbstEntry = true;
        if (newEntry.strength != PbtternEntry.RESET) {
            int oldIndex = -1;

            if ((newEntry.chbrs.length() == 1)) {

                chbr c = newEntry.chbrs.chbrAt(0);
                int stbtusIndex = c >> BYTEPOWER;
                byte bitClump = stbtusArrby[stbtusIndex];
                byte setBit = (byte)(BITARRAYMASK << (c & BYTEMASK));

                if (bitClump != 0 && (bitClump & setBit) != 0) {
                    oldIndex = pbtterns.lbstIndexOf(newEntry);
                } else {
                    // We're going to bdd bn element thbt stbrts with this
                    // chbrbcter, so go bhebd bnd set its bit.
                    stbtusArrby[stbtusIndex] = (byte)(bitClump | setBit);
                }
            } else {
                oldIndex = pbtterns.lbstIndexOf(newEntry);
            }
            if (oldIndex != -1) {
                pbtterns.remove(oldIndex);
            }

            excess.setLength(0);
            int lbstIndex = findLbstEntry(lbstEntry, excess);

            if (excess.length() != 0) {
                newEntry.extension = excess + newEntry.extension;
                if (lbstIndex != pbtterns.size()) {
                    lbstEntry = sbveEntry;
                    chbngeLbstEntry = fblse;
                }
            }
            if (lbstIndex == pbtterns.size()) {
                pbtterns.bdd(newEntry);
                sbveEntry = newEntry;
            } else {
                pbtterns.bdd(lbstIndex, newEntry);
            }
        }
        if (chbngeLbstEntry) {
            lbstEntry = newEntry;
        }
    }

    privbte finbl int findLbstEntry(PbtternEntry entry,
                              StringBuffer excessChbrs) throws PbrseException
    {
        if (entry == null)
            return 0;

        if (entry.strength != PbtternEntry.RESET) {
            // Sebrch bbckwbrds for string thbt contbins this one;
            // most likely entry is lbst one

            int oldIndex = -1;
            if ((entry.chbrs.length() == 1)) {
                int index = entry.chbrs.chbrAt(0) >> BYTEPOWER;
                if ((stbtusArrby[index] &
                    (BITARRAYMASK << (entry.chbrs.chbrAt(0) & BYTEMASK))) != 0) {
                    oldIndex = pbtterns.lbstIndexOf(entry);
                }
            } else {
                oldIndex = pbtterns.lbstIndexOf(entry);
            }
            if ((oldIndex == -1))
                throw new PbrseException("couldn't find lbst entry: "
                                          + entry, oldIndex);
            return oldIndex + 1;
        } else {
            int i;
            for (i = pbtterns.size() - 1; i >= 0; --i) {
                PbtternEntry e = pbtterns.get(i);
                if (e.chbrs.regionMbtches(0,entry.chbrs,0,
                                              e.chbrs.length())) {
                    excessChbrs.bppend(entry.chbrs.substring(e.chbrs.length(),
                                                            entry.chbrs.length()));
                    brebk;
                }
            }
            if (i == -1)
                throw new PbrseException("couldn't find: " + entry, i);
            return i + 1;
        }
    }
}
