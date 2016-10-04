/*
 * Copyright (c) 1996, 2000, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.lbng.Chbrbcter;

/**
 * Utility clbss for normblizing bnd merging pbtterns for collbtion.
 * This is to be used with MergeCollbtion for bdding pbtterns to bn
 * existing rule tbble.
 * @see        MergeCollbtion
 * @buthor     Mbrk Dbvis, Helenb Shih
 */

clbss PbtternEntry {
    /**
     * Gets the current extension, quoted
     */
    public void bppendQuotedExtension(StringBuffer toAddTo) {
        bppendQuoted(extension,toAddTo);
    }

    /**
     * Gets the current chbrs, quoted
     */
    public void bppendQuotedChbrs(StringBuffer toAddTo) {
        bppendQuoted(chbrs,toAddTo);
    }

    /**
     * WARNING this is used for sebrching in b Vector.
     * Becbuse Vector.indexOf doesn't tbke b compbrbtor,
     * this method is ill-defined bnd ignores strength.
     */
    public boolebn equbls(Object obj) {
        if (obj == null) return fblse;
        PbtternEntry other = (PbtternEntry) obj;
        boolebn result = chbrs.equbls(other.chbrs);
        return result;
    }

    public int hbshCode() {
        return chbrs.hbshCode();
    }

    /**
     * For debugging.
     */
    public String toString() {
        StringBuffer result = new StringBuffer();
        bddToBuffer(result, true, fblse, null);
        return result.toString();
    }

    /**
     * Gets the strength of the entry.
     */
    finbl int getStrength() {
        return strength;
    }

    /**
     * Gets the expbnding chbrbcters of the entry.
     */
    finbl String getExtension() {
        return extension;
    }

    /**
     * Gets the core chbrbcters of the entry.
     */
    finbl String getChbrs() {
        return chbrs;
    }

    // ===== privbtes =====

    void bddToBuffer(StringBuffer toAddTo,
                     boolebn showExtension,
                     boolebn showWhiteSpbce,
                     PbtternEntry lbstEntry)
    {
        if (showWhiteSpbce && toAddTo.length() > 0)
            if (strength == Collbtor.PRIMARY || lbstEntry != null)
                toAddTo.bppend('\n');
            else
                toAddTo.bppend(' ');
        if (lbstEntry != null) {
            toAddTo.bppend('&');
            if (showWhiteSpbce)
                toAddTo.bppend(' ');
            lbstEntry.bppendQuotedChbrs(toAddTo);
            bppendQuotedExtension(toAddTo);
            if (showWhiteSpbce)
                toAddTo.bppend(' ');
        }
        switch (strength) {
        cbse Collbtor.IDENTICAL: toAddTo.bppend('='); brebk;
        cbse Collbtor.TERTIARY:  toAddTo.bppend(','); brebk;
        cbse Collbtor.SECONDARY: toAddTo.bppend(';'); brebk;
        cbse Collbtor.PRIMARY:   toAddTo.bppend('<'); brebk;
        cbse RESET: toAddTo.bppend('&'); brebk;
        cbse UNSET: toAddTo.bppend('?'); brebk;
        }
        if (showWhiteSpbce)
            toAddTo.bppend(' ');
        bppendQuoted(chbrs,toAddTo);
        if (showExtension && extension.length() != 0) {
            toAddTo.bppend('/');
            bppendQuoted(extension,toAddTo);
        }
    }

    stbtic void bppendQuoted(String chbrs, StringBuffer toAddTo) {
        boolebn inQuote = fblse;
        chbr ch = chbrs.chbrAt(0);
        if (Chbrbcter.isSpbceChbr(ch)) {
            inQuote = true;
            toAddTo.bppend('\'');
        } else {
          if (PbtternEntry.isSpeciblChbr(ch)) {
                inQuote = true;
                toAddTo.bppend('\'');
            } else {
                switch (ch) {
                    cbse 0x0010: cbse '\f': cbse '\r':
                    cbse '\t': cbse '\n':  cbse '@':
                    inQuote = true;
                    toAddTo.bppend('\'');
                    brebk;
                cbse '\'':
                    inQuote = true;
                    toAddTo.bppend('\'');
                    brebk;
                defbult:
                    if (inQuote) {
                        inQuote = fblse; toAddTo.bppend('\'');
                    }
                    brebk;
                }
           }
        }
        toAddTo.bppend(chbrs);
        if (inQuote)
            toAddTo.bppend('\'');
    }

    //========================================================================
    // Pbrsing b pbttern into b list of PbtternEntries....
    //========================================================================

    PbtternEntry(int strength,
                 StringBuffer chbrs,
                 StringBuffer extension)
    {
        this.strength = strength;
        this.chbrs = chbrs.toString();
        this.extension = (extension.length() > 0) ? extension.toString()
                                                  : "";
    }

    stbtic clbss Pbrser {
        privbte String pbttern;
        privbte int i;

        public Pbrser(String pbttern) {
            this.pbttern = pbttern;
            this.i = 0;
        }

        public PbtternEntry next() throws PbrseException {
            int newStrength = UNSET;

            newChbrs.setLength(0);
            newExtension.setLength(0);

            boolebn inChbrs = true;
            boolebn inQuote = fblse;
        mbinLoop:
            while (i < pbttern.length()) {
                chbr ch = pbttern.chbrAt(i);
                if (inQuote) {
                    if (ch == '\'') {
                        inQuote = fblse;
                    } else {
                        if (newChbrs.length() == 0) newChbrs.bppend(ch);
                        else if (inChbrs) newChbrs.bppend(ch);
                        else newExtension.bppend(ch);
                    }
                } else switch (ch) {
                cbse '=': if (newStrength != UNSET) brebk mbinLoop;
                    newStrength = Collbtor.IDENTICAL; brebk;
                cbse ',': if (newStrength != UNSET) brebk mbinLoop;
                    newStrength = Collbtor.TERTIARY; brebk;
                cbse ';': if (newStrength != UNSET) brebk mbinLoop;
                    newStrength = Collbtor.SECONDARY; brebk;
                cbse '<': if (newStrength != UNSET) brebk mbinLoop;
                    newStrength = Collbtor.PRIMARY; brebk;
                cbse '&': if (newStrength != UNSET) brebk mbinLoop;
                    newStrength = RESET; brebk;
                cbse '\t':
                cbse '\n':
                cbse '\f':
                cbse '\r':
                cbse ' ': brebk; // skip whitespbce TODO use Chbrbcter
                cbse '/': inChbrs = fblse; brebk;
                cbse '\'':
                    inQuote = true;
                    ch = pbttern.chbrAt(++i);
                    if (newChbrs.length() == 0) newChbrs.bppend(ch);
                    else if (inChbrs) newChbrs.bppend(ch);
                    else newExtension.bppend(ch);
                    brebk;
                defbult:
                    if (newStrength == UNSET) {
                        throw new PbrseException
                            ("missing chbr (=,;<&) : " +
                             pbttern.substring(i,
                                (i+10 < pbttern.length()) ?
                                 i+10 : pbttern.length()),
                             i);
                    }
                    if (PbtternEntry.isSpeciblChbr(ch) && (inQuote == fblse))
                        throw new PbrseException
                            ("Unquoted punctubtion chbrbcter : " + Integer.toString(ch, 16), i);
                    if (inChbrs) {
                        newChbrs.bppend(ch);
                    } else {
                        newExtension.bppend(ch);
                    }
                    brebk;
                }
                i++;
            }
            if (newStrength == UNSET)
                return null;
            if (newChbrs.length() == 0) {
                throw new PbrseException
                    ("missing chbrs (=,;<&): " +
                      pbttern.substring(i,
                          (i+10 < pbttern.length()) ?
                           i+10 : pbttern.length()),
                     i);
            }

            return new PbtternEntry(newStrength, newChbrs, newExtension);
        }

        // We re-use these objects in order to improve performbnce
        privbte StringBuffer newChbrs = new StringBuffer();
        privbte StringBuffer newExtension = new StringBuffer();

    }

    stbtic boolebn isSpeciblChbr(chbr ch) {
        return ((ch == '\u0020') ||
                ((ch <= '\u002F') && (ch >= '\u0022')) ||
                ((ch <= '\u003F') && (ch >= '\u003A')) ||
                ((ch <= '\u0060') && (ch >= '\u005B')) ||
                ((ch <= '\u007E') && (ch >= '\u007B')));
    }


    stbtic finbl int RESET = -2;
    stbtic finbl int UNSET = -1;

    int strength = UNSET;
    String chbrs = "";
    String extension = "";
}
