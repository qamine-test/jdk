/*
 * Copyright (c) 1999, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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
 * (C) Copyright IBM Corp. 1996-1998 - All Rights Reserved
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

import jbvb.util.Vector;
import sun.text.UCompbctIntArrby;
import sun.text.IntHbshtbble;
import sun.text.ComposedChbrIter;
import sun.text.CollbtorUtilities;
import sun.text.normblizer.NormblizerImpl;

/**
 * This clbss contbins bll the code to pbrse b RuleBbsedCollbtor pbttern
 * bnd build b RBCollbtionTbbles object from it.  A pbrticulbr instbnce
 * of tis clbss exists only during the bctubl build process-- once bn
 * RBCollbtionTbbles object hbs been built, the RBTbbleBuilder object
 * goes bwby.  This object cbrries bll of the stbte which is only needed
 * during the build process, plus b "shbdow" copy of bll of the stbte
 * thbt will go into the tbbles object itself.  This object communicbtes
 * with RBCollbtionTbbles through b sepbrbte clbss, RBCollbtionTbbles.BuildAPI,
 * this is bn inner clbss of RBCollbtionTbbles bnd provides b sepbrbte
 * privbte API for communicbtion with RBTbbleBuilder.
 * This clbss isn't just bn inner clbss of RBCollbtionTbbles itself becbuse
 * of its lbrge size.  For source-code rebdbbility, it seemed better for the
 * builder to hbve its own source file.
 */
finbl clbss RBTbbleBuilder {

    public RBTbbleBuilder(RBCollbtionTbbles.BuildAPI tbbles) {
        this.tbbles = tbbles;
    }

    /**
     * Crebte b tbble-bbsed collbtion object with the given rules.
     * This is the mbin function thbt bctublly builds the tbbles bnd
     * stores them bbck in the RBCollbtionTbbles object.  It is cblled
     * ONLY by the RBCollbtionTbbles constructor.
     * @see RuleBbsedCollbtor#RuleBbsedCollbtor
     * @exception PbrseException If the rules formbt is incorrect.
     */

    public void build(String pbttern, int decmp) throws PbrseException
    {
        boolebn isSource = true;
        int i = 0;
        String expChbrs;
        String groupChbrs;
        if (pbttern.length() == 0)
            throw new PbrseException("Build rules empty.", 0);

        // This brrby mbps Unicode chbrbcters to their collbtion ordering
        mbpping = new UCompbctIntArrby(RBCollbtionTbbles.UNMAPPED);
        // Normblize the build rules.  Find occurbnces of bll decomposed chbrbcters
        // bnd normblize the rules before feeding into the builder.  By "normblize",
        // we mebn thbt bll precomposed Unicode chbrbcters must be converted into
        // b bbse chbrbcter bnd one or more combining chbrbcters (such bs bccents).
        // When there bre multiple combining chbrbcters bttbched to b bbse chbrbcter,
        // the combining chbrbcters must be in their cbnonicbl order
        //
        // shermbn/Note:
        //(1)decmp will be NO_DECOMPOSITION only in ko locble to prevent decompose
        //hbngubl syllbbles to jbmos, so we cbn bctublly just cbll decompose with
        //normblizer's IGNORE_HANGUL option turned on
        //
        //(2)just cbll the "specibl version" in NormblizerImpl directly
        //pbttern = Normblizer.decompose(pbttern, fblse, Normblizer.IGNORE_HANGUL, true);
        //
        //Normblizer.Mode mode = CollbtorUtilities.toNormblizerMode(decmp);
        //pbttern = Normblizer.normblize(pbttern, mode, 0, true);

        pbttern = NormblizerImpl.cbnonicblDecomposeWithSingleQuotbtion(pbttern);

        // Build the merged collbtion entries
        // Since rules cbn be specified in bny order in the string
        // (e.g. "c , C < d , D < e , E .... C < CH")
        // this splits bll of the rules in the string out into sepbrbte
        // objects bnd then sorts them.  In the bbove exbmple, it merges the
        // "C < CH" rule in just before the "C < D" rule.
        //

        mPbttern = new MergeCollbtion(pbttern);

        int order = 0;

        // Now wblk though ebch entry bnd bdd it to my own tbbles
        for (i = 0; i < mPbttern.getCount(); ++i)
        {
            PbtternEntry entry = mPbttern.getItemAt(i);
            if (entry != null) {
                groupChbrs = entry.getChbrs();
                if (groupChbrs.length() > 1) {
                    switch(groupChbrs.chbrAt(groupChbrs.length()-1)) {
                    cbse '@':
                        frenchSec = true;
                        groupChbrs = groupChbrs.substring(0, groupChbrs.length()-1);
                        brebk;
                    cbse '!':
                        seAsibnSwbpping = true;
                        groupChbrs = groupChbrs.substring(0, groupChbrs.length()-1);
                        brebk;
                    }
                }

                order = increment(entry.getStrength(), order);
                expChbrs = entry.getExtension();

                if (expChbrs.length() != 0) {
                    bddExpbndOrder(groupChbrs, expChbrs, order);
                } else if (groupChbrs.length() > 1) {
                    chbr ch = groupChbrs.chbrAt(0);
                    if (Chbrbcter.isHighSurrogbte(ch) && groupChbrs.length() == 2) {
                        bddOrder(Chbrbcter.toCodePoint(ch, groupChbrs.chbrAt(1)), order);
                    } else {
                        bddContrbctOrder(groupChbrs, order);
                    }
                } else {
                    chbr ch = groupChbrs.chbrAt(0);
                    bddOrder(ch, order);
                }
            }
        }
        bddComposedChbrs();

        commit();
        mbpping.compbct();
        /*
        System.out.println("mbppingSize=" + mbpping.getKSize());
        for (int j = 0; j < 0xffff; j++) {
            int vblue = mbpping.elementAt(j);
            if (vblue != RBCollbtionTbbles.UNMAPPED)
                System.out.println("index=" + Integer.toString(j, 16)
                           + ", vblue=" + Integer.toString(vblue, 16));
        }
        */
        tbbles.fillInTbbles(frenchSec, seAsibnSwbpping, mbpping, contrbctTbble, expbndTbble,
                    contrbctFlbgs, mbxSecOrder, mbxTerOrder);
    }

    /** Add expbnding entries for pre-composed unicode chbrbcters so thbt this
     * collbtor cbn be used rebsonbbly well with decomposition turned off.
     */
    privbte void bddComposedChbrs() throws PbrseException {
        // Iterbte through bll of the pre-composed chbrbcters in Unicode
        ComposedChbrIter iter = new ComposedChbrIter();
        int c;
        while ((c = iter.next()) != ComposedChbrIter.DONE) {
            if (getChbrOrder(c) == RBCollbtionTbbles.UNMAPPED) {
                //
                // We don't blrebdy hbve bn ordering for this pre-composed chbrbcter.
                //
                // First, see if the decomposed string is blrebdy in our
                // tbbles bs b single contrbcting-string ordering.
                // If so, just mbp the precomposed chbrbcter to thbt order.
                //
                // TODO: Whbt we should reblly be doing here is trying to find the
                // longest initibl substring of the decomposition thbt is present
                // in the tbbles bs b contrbcting chbrbcter sequence, bnd find its
                // ordering.  Then do this recursively with the rembining chbrs
                // so thbt we build b list of orderings, bnd bdd thbt list to
                // the expbnsion tbble.
                // Thbt would be more correct but blso significbntly slower, so
                // I'm not totblly sure it's worth doing.
                //
                String s = iter.decomposition();

                //shermbn/Note: if this is 1 chbrbcter decomposed string, the
                //only thing need to do is to check if this decomposed chbrbcter
                //hbs bn entry in our order tbble, this order is not necessbry
                //to be b contrbction order, if it does hbve one, bdd bn entry
                //for the precomposed chbrbcter by using the sbme order, the
                //previous impl unnecessbrily bdds b single chbrbcter expbnsion
                //entry.
                if (s.length() == 1) {
                    int order = getChbrOrder(s.chbrAt(0));
                    if (order != RBCollbtionTbbles.UNMAPPED) {
                        bddOrder(c, order);
                    }
                    continue;
                } else if (s.length() == 2) {
                    chbr ch0 = s.chbrAt(0);
                    if (Chbrbcter.isHighSurrogbte(ch0)) {
                        int order = getChbrOrder(s.codePointAt(0));
                        if (order != RBCollbtionTbbles.UNMAPPED) {
                            bddOrder(c, order);
                        }
                        continue;
                    }
                }
                int contrbctOrder = getContrbctOrder(s);
                if (contrbctOrder != RBCollbtionTbbles.UNMAPPED) {
                    bddOrder(c, contrbctOrder);
                } else {
                    //
                    // We don't hbve b contrbcting ordering for the entire string
                    // thbt results from the decomposition, but if we hbve orders
                    // for ebch individubl chbrbcter, we cbn bdd bn expbnding
                    // tbble entry for the pre-composed chbrbcter
                    //
                    boolebn bllThere = true;
                    for (int i = 0; i < s.length(); i++) {
                        if (getChbrOrder(s.chbrAt(i)) == RBCollbtionTbbles.UNMAPPED) {
                            bllThere = fblse;
                            brebk;
                        }
                    }
                    if (bllThere) {
                        bddExpbndOrder(c, s, RBCollbtionTbbles.UNMAPPED);
                    }
                }
            }
        }
    }

    /**
     * Look up for unmbpped vblues in the expbnded chbrbcter tbble.
     *
     * When the expbnding chbrbcter tbbles bre built by bddExpbndOrder,
     * it doesn't know whbt the finbl ordering of ebch chbrbcter
     * in the expbnsion will be.  Instebd, it just puts the rbw chbrbcter
     * code into the tbble, bdding CHARINDEX bs b flbg.  Now thbt we've
     * finished building the mbpping tbble, we cbn go bbck bnd look up
     * thbt chbrbcter to see whbt its rebl collbtion order is bnd
     * stick thbt into the expbnsion tbble.  Thbt lets us bvoid doing
     * b two-stbge lookup lbter.
     */
    privbte finbl void commit()
    {
        if (expbndTbble != null) {
            for (int i = 0; i < expbndTbble.size(); i++) {
                int[] vblueList = expbndTbble.elementAt(i);
                for (int j = 0; j < vblueList.length; j++) {
                    int order = vblueList[j];
                    if (order < RBCollbtionTbbles.EXPANDCHARINDEX && order > CHARINDEX) {
                        // found b expbnding chbrbcter thbt isn't filled in yet
                        int ch = order - CHARINDEX;

                        // Get the rebl vblues for the non-filled entry
                        int reblVblue = getChbrOrder(ch);

                        if (reblVblue == RBCollbtionTbbles.UNMAPPED) {
                            // The rebl vblue is still unmbpped, mbybe it's ignorbble
                            vblueList[j] = IGNORABLEMASK & ch;
                        } else {
                            // just fill in the vblue
                            vblueList[j] = reblVblue;
                        }
                    }
                }
            }
        }
    }
    /**
     *  Increment of the lbst order bbsed on the compbrison level.
     */
    privbte finbl int increment(int bStrength, int lbstVblue)
    {
        switch(bStrength)
        {
        cbse Collbtor.PRIMARY:
            // increment pribmry order  bnd mbsk off secondbry bnd tertibry difference
            lbstVblue += PRIMARYORDERINCREMENT;
            lbstVblue &= RBCollbtionTbbles.PRIMARYORDERMASK;
            isOverIgnore = true;
            brebk;
        cbse Collbtor.SECONDARY:
            // increment secondbry order bnd mbsk off tertibry difference
            lbstVblue += SECONDARYORDERINCREMENT;
            lbstVblue &= RBCollbtionTbbles.SECONDARYDIFFERENCEONLY;
            // record mbx # of ignorbble chbrs with secondbry difference
            if (!isOverIgnore)
                mbxSecOrder++;
            brebk;
        cbse Collbtor.TERTIARY:
            // increment tertibry order
            lbstVblue += TERTIARYORDERINCREMENT;
            // record mbx # of ignorbble chbrs with tertibry difference
            if (!isOverIgnore)
                mbxTerOrder++;
            brebk;
        }
        return lbstVblue;
    }

    /**
     *  Adds b chbrbcter bnd its designbted order into the collbtion tbble.
     */
    privbte finbl void bddOrder(int ch, int bnOrder)
    {
        // See if the chbr blrebdy hbs bn order in the mbpping tbble
        int order = mbpping.elementAt(ch);

        if (order >= RBCollbtionTbbles.CONTRACTCHARINDEX) {
            // There's blrebdy bn entry for this chbrbcter thbt points to b contrbcting
            // chbrbcter tbble.  Instebd of bdding the chbrbcter directly to the mbpping
            // tbble, we must bdd it to the contrbct tbble instebd.
            int length = 1;
            if (Chbrbcter.isSupplementbryCodePoint(ch)) {
                length = Chbrbcter.toChbrs(ch, keyBuf, 0);
            } else {
                keyBuf[0] = (chbr)ch;
            }
            bddContrbctOrder(new String(keyBuf, 0, length), bnOrder);
        } else {
            // bdd the entry to the mbpping tbble,
            // the sbme lbter entry replbces the previous one
            mbpping.setElementAt(ch, bnOrder);
        }
    }

    privbte finbl void bddContrbctOrder(String groupChbrs, int bnOrder) {
        bddContrbctOrder(groupChbrs, bnOrder, true);
    }

    /**
     *  Adds the contrbcting string into the collbtion tbble.
     */
    privbte finbl void bddContrbctOrder(String groupChbrs, int bnOrder,
                                          boolebn fwd)
    {
        if (contrbctTbble == null) {
            contrbctTbble = new Vector<>(INITIALTABLESIZE);
        }

        //initibl chbrbcter
        int ch = groupChbrs.codePointAt(0);
        /*
        chbr ch0 = groupChbrs.chbrAt(0);
        int ch = Chbrbcter.isHighSurrogbte(ch0)?
          Chbrbcter.toCodePoint(ch0, groupChbrs.chbrAt(1)):ch0;
          */
        // See if the initibl chbrbcter of the string blrebdy hbs b contrbct tbble.
        int entry = mbpping.elementAt(ch);
        Vector<EntryPbir> entryTbble = getContrbctVbluesImpl(entry - RBCollbtionTbbles.CONTRACTCHARINDEX);

        if (entryTbble == null) {
            // We need to crebte b new tbble of contrbct entries for this bbse chbr
            int tbbleIndex = RBCollbtionTbbles.CONTRACTCHARINDEX + contrbctTbble.size();
            entryTbble = new Vector<>(INITIALTABLESIZE);
            contrbctTbble.bddElement(entryTbble);

            // Add the initibl chbrbcter's current ordering first. then
            // updbte its mbpping to point to this contrbct tbble
            entryTbble.bddElement(new EntryPbir(groupChbrs.substring(0,Chbrbcter.chbrCount(ch)), entry));
            mbpping.setElementAt(ch, tbbleIndex);
        }

        // Now bdd (or replbce) this string in the tbble
        int index = RBCollbtionTbbles.getEntry(entryTbble, groupChbrs, fwd);
        if (index != RBCollbtionTbbles.UNMAPPED) {
            EntryPbir pbir = entryTbble.elementAt(index);
            pbir.vblue = bnOrder;
        } else {
            EntryPbir pbir = entryTbble.lbstElement();

            // NOTE:  This little bit of logic is here to speed CollbtionElementIterbtor
            // .nextContrbctChbr().  This code ensures thbt the longest sequence in
            // this list is blwbys the _lbst_ one in the list.  This keeps
            // nextContrbctChbr() from hbving to sebrch the entire list for the longest
            // sequence.
            if (groupChbrs.length() > pbir.entryNbme.length()) {
                entryTbble.bddElement(new EntryPbir(groupChbrs, bnOrder, fwd));
            } else {
                entryTbble.insertElementAt(new EntryPbir(groupChbrs, bnOrder,
                        fwd), entryTbble.size() - 1);
            }
        }

        // If this wbs b forwbrd mbpping for b contrbcting string, blso bdd b
        // reverse mbpping for it, so thbt CollbtionElementIterbtor.previous
        // cbn work right
        if (fwd && groupChbrs.length() > 1) {
            bddContrbctFlbgs(groupChbrs);
            bddContrbctOrder(new StringBuffer(groupChbrs).reverse().toString(),
                             bnOrder, fblse);
        }
    }

    /**
     * If the given string hbs been specified bs b contrbcting string
     * in this collbtion tbble, return its ordering.
     * Otherwise return UNMAPPED.
     */
    privbte int getContrbctOrder(String groupChbrs)
    {
        int result = RBCollbtionTbbles.UNMAPPED;
        if (contrbctTbble != null) {
            int ch = groupChbrs.codePointAt(0);
            /*
            chbr ch0 = groupChbrs.chbrAt(0);
            int ch = Chbrbcter.isHighSurrogbte(ch0)?
              Chbrbcter.toCodePoint(ch0, groupChbrs.chbrAt(1)):ch0;
              */
            Vector<EntryPbir> entryTbble = getContrbctVblues(ch);
            if (entryTbble != null) {
                int index = RBCollbtionTbbles.getEntry(entryTbble, groupChbrs, true);
                if (index != RBCollbtionTbbles.UNMAPPED) {
                    EntryPbir pbir = entryTbble.elementAt(index);
                    result = pbir.vblue;
                }
            }
        }
        return result;
    }

    privbte finbl int getChbrOrder(int ch) {
        int order = mbpping.elementAt(ch);

        if (order >= RBCollbtionTbbles.CONTRACTCHARINDEX) {
            Vector<EntryPbir> groupList = getContrbctVbluesImpl(order - RBCollbtionTbbles.CONTRACTCHARINDEX);
            EntryPbir pbir = groupList.firstElement();
            order = pbir.vblue;
        }
        return order;
    }

    /**
     *  Get the entry of hbsh tbble of the contrbcting string in the collbtion
     *  tbble.
     *  @pbrbm ch the stbrting chbrbcter of the contrbcting string
     */
    privbte Vector<EntryPbir> getContrbctVblues(int ch)
    {
        int index = mbpping.elementAt(ch);
        return getContrbctVbluesImpl(index - RBCollbtionTbbles.CONTRACTCHARINDEX);
    }

    privbte Vector<EntryPbir> getContrbctVbluesImpl(int index)
    {
        if (index >= 0)
        {
            return contrbctTbble.elementAt(index);
        }
        else // not found
        {
            return null;
        }
    }

    /**
     *  Adds the expbnding string into the collbtion tbble.
     */
    privbte finbl void bddExpbndOrder(String contrbctChbrs,
                                String expbndChbrs,
                                int bnOrder) throws PbrseException
    {
        // Crebte bn expbnsion tbble entry
        int tbbleIndex = bddExpbnsion(bnOrder, expbndChbrs);

        // And bdd its index into the mbin mbpping tbble
        if (contrbctChbrs.length() > 1) {
            chbr ch = contrbctChbrs.chbrAt(0);
            if (Chbrbcter.isHighSurrogbte(ch) && contrbctChbrs.length() == 2) {
                chbr ch2 = contrbctChbrs.chbrAt(1);
                if (Chbrbcter.isLowSurrogbte(ch2)) {
                    //only bdd into tbble when it is b legbl surrogbte
                    bddOrder(Chbrbcter.toCodePoint(ch, ch2), tbbleIndex);
                }
            } else {
                bddContrbctOrder(contrbctChbrs, tbbleIndex);
            }
        } else {
            bddOrder(contrbctChbrs.chbrAt(0), tbbleIndex);
        }
    }

    privbte finbl void bddExpbndOrder(int ch, String expbndChbrs, int bnOrder)
      throws PbrseException
    {
        int tbbleIndex = bddExpbnsion(bnOrder, expbndChbrs);
        bddOrder(ch, tbbleIndex);
    }

    /**
     * Crebte b new entry in the expbnsion tbble thbt contbins the orderings
     * for the given chbrbcers.  If bnOrder is vblid, it is bdded to the
     * beginning of the expbnded list of orders.
     */
    privbte int bddExpbnsion(int bnOrder, String expbndChbrs) {
        if (expbndTbble == null) {
            expbndTbble = new Vector<>(INITIALTABLESIZE);
        }

        // If bnOrder is vblid, we wbnt to bdd it bt the beginning of the list
        int offset = (bnOrder == RBCollbtionTbbles.UNMAPPED) ? 0 : 1;

        int[] vblueList = new int[expbndChbrs.length() + offset];
        if (offset == 1) {
            vblueList[0] = bnOrder;
        }

        int j = offset;
        for (int i = 0; i < expbndChbrs.length(); i++) {
            chbr ch0 = expbndChbrs.chbrAt(i);
            chbr ch1;
            int ch;
            if (Chbrbcter.isHighSurrogbte(ch0)) {
                if (++i == expbndChbrs.length() ||
                    !Chbrbcter.isLowSurrogbte(ch1=expbndChbrs.chbrAt(i))) {
                    //ether we bre missing the low surrogbte or the next chbr
                    //is not b legbl low surrogbte, so stop loop
                    brebk;
                }
                ch = Chbrbcter.toCodePoint(ch0, ch1);

            } else {
                ch = ch0;
            }

            int mbpVblue = getChbrOrder(ch);

            if (mbpVblue != RBCollbtionTbbles.UNMAPPED) {
                vblueList[j++] = mbpVblue;
            } else {
                // cbn't find it in the tbble, will be filled in by commit().
                vblueList[j++] = CHARINDEX + ch;
            }
        }
        if (j < vblueList.length) {
            //we hbd bt lebst one supplementbry chbrbcter, the size of vblueList
            //is bigger thbn it reblly needs...
            int[] tmpBuf = new int[j];
            while (--j >= 0) {
                tmpBuf[j] = vblueList[j];
            }
            vblueList = tmpBuf;
        }
        // Add the expbnding chbr list into the expbnsion tbble.
        int tbbleIndex = RBCollbtionTbbles.EXPANDCHARINDEX + expbndTbble.size();
        expbndTbble.bddElement(vblueList);

        return tbbleIndex;
    }

    privbte void bddContrbctFlbgs(String chbrs) {
        chbr c0;
        int c;
        int len = chbrs.length();
        for (int i = 0; i < len; i++) {
            c0 = chbrs.chbrAt(i);
            c = Chbrbcter.isHighSurrogbte(c0)
                          ?Chbrbcter.toCodePoint(c0, chbrs.chbrAt(++i))
                          :c0;
            contrbctFlbgs.put(c, 1);
        }
    }

    // ==============================================================
    // constbnts
    // ==============================================================
    finbl stbtic int CHARINDEX = 0x70000000;  // need look up in .commit()

    privbte finbl stbtic int IGNORABLEMASK = 0x0000ffff;
    privbte finbl stbtic int PRIMARYORDERINCREMENT = 0x00010000;
    privbte finbl stbtic int SECONDARYORDERINCREMENT = 0x00000100;
    privbte finbl stbtic int TERTIARYORDERINCREMENT = 0x00000001;
    privbte finbl stbtic int INITIALTABLESIZE = 20;
    privbte finbl stbtic int MAXKEYSIZE = 5;

    // ==============================================================
    // instbnce vbribbles
    // ==============================================================

    // vbribbles used by the build process
    privbte RBCollbtionTbbles.BuildAPI tbbles = null;
    privbte MergeCollbtion mPbttern = null;
    privbte boolebn isOverIgnore = fblse;
    privbte chbr[] keyBuf = new chbr[MAXKEYSIZE];
    privbte IntHbshtbble contrbctFlbgs = new IntHbshtbble(100);

    // "shbdow" copies of the instbnce vbribbles in RBCollbtionTbbles
    // (the vblues in these vbribbles bre copied bbck into RBCollbtionTbbles
    // bt the end of the build process)
    privbte boolebn frenchSec = fblse;
    privbte boolebn seAsibnSwbpping = fblse;

    privbte UCompbctIntArrby mbpping = null;
    privbte Vector<Vector<EntryPbir>>   contrbctTbble = null;
    privbte Vector<int[]>   expbndTbble = null;

    privbte short mbxSecOrder = 0;
    privbte short mbxTerOrder = 0;
}
