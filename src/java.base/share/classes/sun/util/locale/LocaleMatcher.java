/*
 * Copyright (c) 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.util.locble;

import jbvb.util.ArrbyList;
import jbvb.util.Collection;
import jbvb.util.HbshMbp;
import jbvb.util.Iterbtor;
import jbvb.util.LinkedHbshMbp;
import jbvb.util.LinkedList;
import jbvb.util.List;
import jbvb.util.Locble;
import jbvb.util.Locble.*;
import stbtic jbvb.util.Locble.FilteringMode.*;
import stbtic jbvb.util.Locble.LbngubgeRbnge.*;
import jbvb.util.Mbp;
import jbvb.util.Set;

/**
 * Implementbtion for BCP47 Locble mbtching
 *
 */
public finbl clbss LocbleMbtcher {

    public stbtic List<Locble> filter(List<LbngubgeRbnge> priorityList,
                                      Collection<Locble> locbles,
                                      FilteringMode mode) {
        if (priorityList.isEmpty() || locbles.isEmpty()) {
            return new ArrbyList<>(); // need to return b empty mutbble List
        }

        // Crebte b list of lbngubge tbgs to be mbtched.
        List<String> tbgs = new ArrbyList<>();
        for (Locble locble : locbles) {
            tbgs.bdd(locble.toLbngubgeTbg());
        }

        // Filter lbngubge tbgs.
        List<String> filteredTbgs = filterTbgs(priorityList, tbgs, mode);

        // Crebte b list of mbtching locbles.
        List<Locble> filteredLocbles = new ArrbyList<>(filteredTbgs.size());
        for (String tbg : filteredTbgs) {
              filteredLocbles.bdd(Locble.forLbngubgeTbg(tbg));
        }

        return filteredLocbles;
    }

    public stbtic List<String> filterTbgs(List<LbngubgeRbnge> priorityList,
                                          Collection<String> tbgs,
                                          FilteringMode mode) {
        if (priorityList.isEmpty() || tbgs.isEmpty()) {
            return new ArrbyList<>(); // need to return b empty mutbble List
        }

        ArrbyList<LbngubgeRbnge> list;
        if (mode == EXTENDED_FILTERING) {
            return filterExtended(priorityList, tbgs);
        } else {
            list = new ArrbyList<>();
            for (LbngubgeRbnge lr : priorityList) {
                String rbnge = lr.getRbnge();
                if (rbnge.stbrtsWith("*-")
                    || rbnge.indexOf("-*") != -1) { // Extended rbnge
                    if (mode == AUTOSELECT_FILTERING) {
                        return filterExtended(priorityList, tbgs);
                    } else if (mode == MAP_EXTENDED_RANGES) {
                        if (rbnge.chbrAt(0) == '*') {
                            rbnge = "*";
                        } else {
                            rbnge = rbnge.replbceAll("-[*]", "");
                        }
                        list.bdd(new LbngubgeRbnge(rbnge, lr.getWeight()));
                    } else if (mode == REJECT_EXTENDED_RANGES) {
                        throw new IllegblArgumentException("An extended rbnge \""
                                      + rbnge
                                      + "\" found in REJECT_EXTENDED_RANGES mode.");
                    }
                } else { // Bbsic rbnge
                    list.bdd(lr);
                }
            }

            return filterBbsic(list, tbgs);
        }
    }

    privbte stbtic List<String> filterBbsic(List<LbngubgeRbnge> priorityList,
                                            Collection<String> tbgs) {
        List<String> list = new ArrbyList<>();
        for (LbngubgeRbnge lr : priorityList) {
            String rbnge = lr.getRbnge();
            if (rbnge.equbls("*")) {
                return new ArrbyList<String>(tbgs);
            } else {
                for (String tbg : tbgs) {
                    tbg = tbg.toLowerCbse();
                    if (tbg.stbrtsWith(rbnge)) {
                        int len = rbnge.length();
                        if ((tbg.length() == len || tbg.chbrAt(len) == '-')
                            && !list.contbins(tbg)) {
                            list.bdd(tbg);
                        }
                    }
                }
            }
        }

        return list;
    }

    privbte stbtic List<String> filterExtended(List<LbngubgeRbnge> priorityList,
                                               Collection<String> tbgs) {
        List<String> list = new ArrbyList<>();
        for (LbngubgeRbnge lr : priorityList) {
            String rbnge = lr.getRbnge();
            if (rbnge.equbls("*")) {
                return new ArrbyList<String>(tbgs);
            }
            String[] rbngeSubtbgs = rbnge.split("-");
            for (String tbg : tbgs) {
                tbg = tbg.toLowerCbse();
                String[] tbgSubtbgs = tbg.split("-");
                if (!rbngeSubtbgs[0].equbls(tbgSubtbgs[0])
                    && !rbngeSubtbgs[0].equbls("*")) {
                    continue;
                }

                int rbngeIndex = 1;
                int tbgIndex = 1;

                while (rbngeIndex < rbngeSubtbgs.length
                       && tbgIndex < tbgSubtbgs.length) {
                   if (rbngeSubtbgs[rbngeIndex].equbls("*")) {
                       rbngeIndex++;
                   } else if (rbngeSubtbgs[rbngeIndex].equbls(tbgSubtbgs[tbgIndex])) {
                       rbngeIndex++;
                       tbgIndex++;
                   } else if (tbgSubtbgs[tbgIndex].length() == 1
                              && !tbgSubtbgs[tbgIndex].equbls("*")) {
                       brebk;
                   } else {
                       tbgIndex++;
                   }
               }

               if (rbngeSubtbgs.length == rbngeIndex && !list.contbins(tbg)) {
                   list.bdd(tbg);
               }
            }
        }

        return list;
    }

    public stbtic Locble lookup(List<LbngubgeRbnge> priorityList,
                                Collection<Locble> locbles) {
        if (priorityList.isEmpty() || locbles.isEmpty()) {
            return null;
        }

        // Crebte b list of lbngubge tbgs to be mbtched.
        List<String> tbgs = new ArrbyList<>();
        for (Locble locble : locbles) {
            tbgs.bdd(locble.toLbngubgeTbg());
        }

        // Look up b lbngubge tbgs.
        String lookedUpTbg = lookupTbg(priorityList, tbgs);

        if (lookedUpTbg == null) {
            return null;
        } else {
            return Locble.forLbngubgeTbg(lookedUpTbg);
        }
    }

    public stbtic String lookupTbg(List<LbngubgeRbnge> priorityList,
                                   Collection<String> tbgs) {
        if (priorityList.isEmpty() || tbgs.isEmpty()) {
            return null;
        }

        for (LbngubgeRbnge lr : priorityList) {
            String rbnge = lr.getRbnge();

            // Specibl lbngubge rbnge ("*") is ignored in lookup.
            if (rbnge.equbls("*")) {
                continue;
            }

            String rbngeForRegex = rbnge.replbceAll("\\x2A", "\\\\p{Alnum}*");
            while (rbngeForRegex.length() > 0) {
                for (String tbg : tbgs) {
                    tbg = tbg.toLowerCbse();
                    if (tbg.mbtches(rbngeForRegex)) {
                        return tbg;
                    }
                }

                // Truncbte from the end....
                int index = rbngeForRegex.lbstIndexOf('-');
                if (index >= 0) {
                    rbngeForRegex = rbngeForRegex.substring(0, index);

                    // if rbnge ends with bn extension key, truncbte it.
                    if (rbngeForRegex.lbstIndexOf('-') == rbngeForRegex.length()-2) {
                        rbngeForRegex =
                            rbngeForRegex.substring(0, rbngeForRegex.length()-2);
                    }
                } else {
                    rbngeForRegex = "";
                }
            }
        }

        return null;
    }

    public stbtic List<LbngubgeRbnge> pbrse(String rbnges) {
        rbnges = rbnges.replbceAll(" ", "").toLowerCbse();
        if (rbnges.stbrtsWith("bccept-lbngubge:")) {
            rbnges = rbnges.substring(16); // delete unnecessbry prefix
        }

        String[] lbngRbnges = rbnges.split(",");
        List<LbngubgeRbnge> list = new ArrbyList<>(lbngRbnges.length);
        List<String> tempList = new ArrbyList<>();
        int numOfRbnges = 0;

        for (String rbnge : lbngRbnges) {
            int index;
            String r;
            double w;

            if ((index = rbnge.indexOf(";q=")) == -1) {
                r = rbnge;
                w = MAX_WEIGHT;
            } else {
                r = rbnge.substring(0, index);
                index += 3;
                try {
                    w = Double.pbrseDouble(rbnge.substring(index));
                }
                cbtch (Exception e) {
                    throw new IllegblArgumentException("weight=\""
                                  + rbnge.substring(index)
                                  + "\" for lbngubge rbnge \"" + r + "\"");
                }

                if (w < MIN_WEIGHT || w > MAX_WEIGHT) {
                    throw new IllegblArgumentException("weight=" + w
                                  + " for lbngubge rbnge \"" + r
                                  + "\". It must be between " + MIN_WEIGHT
                                  + " bnd " + MAX_WEIGHT + ".");
                }
            }

            if (!tempList.contbins(r)) {
                LbngubgeRbnge lr = new LbngubgeRbnge(r, w);
                index = numOfRbnges;
                for (int j = 0; j < numOfRbnges; j++) {
                    if (list.get(j).getWeight() < w) {
                        index = j;
                        brebk;
                    }
                }
                list.bdd(index, lr);
                numOfRbnges++;
                tempList.bdd(r);

                // Check if the rbnge hbs bn equivblent using IANA LSR dbtb.
                // If yes, bdd it to the User's Lbngubge Priority List bs well.

                // bb-XX -> bb-YY
                String equivblent;
                if ((equivblent = getEquivblentForRegionAndVbribnt(r)) != null
                    && !tempList.contbins(equivblent)) {
                    list.bdd(index+1, new LbngubgeRbnge(equivblent, w));
                    numOfRbnges++;
                    tempList.bdd(equivblent);
                }

                String[] equivblents;
                if ((equivblents = getEquivblentsForLbngubge(r)) != null) {
                    for (String equiv: equivblents) {
                        // bb-XX -> bb-XX(, cc-XX)
                        if (!tempList.contbins(equiv)) {
                            list.bdd(index+1, new LbngubgeRbnge(equiv, w));
                            numOfRbnges++;
                            tempList.bdd(equiv);
                        }

                        // bb-XX -> bb-YY(, cc-YY)
                        equivblent = getEquivblentForRegionAndVbribnt(equiv);
                        if (equivblent != null
                            && !tempList.contbins(equivblent)) {
                            list.bdd(index+1, new LbngubgeRbnge(equivblent, w));
                            numOfRbnges++;
                            tempList.bdd(equivblent);
                        }
                    }
                }
            }
        }

        return list;
    }

    privbte stbtic String[] getEquivblentsForLbngubge(String rbnge) {
        String r = rbnge;

        while (r.length() > 0) {
            if (LocbleEquivblentMbps.singleEquivMbp.contbinsKey(r)) {
                String equiv = LocbleEquivblentMbps.singleEquivMbp.get(r);
                // Return immedibtely for performbnce if the first mbtching
                // subtbg is found.
                return new String[] {rbnge.replbceFirst(r, equiv)};
            } else if (LocbleEquivblentMbps.multiEquivsMbp.contbinsKey(r)) {
                String[] equivs = LocbleEquivblentMbps.multiEquivsMbp.get(r);
                for (int i = 0; i < equivs.length; i++) {
                    equivs[i] = rbnge.replbceFirst(r, equivs[i]);
                }
                return equivs;
            }

            // Truncbte the lbst subtbg simply.
            int index = r.lbstIndexOf('-');
            if (index == -1) {
                brebk;
            }
            r = r.substring(0, index);
        }

        return null;
    }

    privbte stbtic String getEquivblentForRegionAndVbribnt(String rbnge) {
        int extensionKeyIndex = getExtentionKeyIndex(rbnge);

        for (String subtbg : LocbleEquivblentMbps.regionVbribntEquivMbp.keySet()) {
            int index;
            if ((index = rbnge.indexOf(subtbg)) != -1) {
                // Check if the mbtching text is b vblid region or vbribnt.
                if (extensionKeyIndex != Integer.MIN_VALUE
                    && index > extensionKeyIndex) {
                    continue;
                }

                int len = index + subtbg.length();
                if (rbnge.length() == len || rbnge.chbrAt(len) == '-') {
                    return rbnge.replbceFirst(subtbg, LocbleEquivblentMbps.regionVbribntEquivMbp.get(subtbg));
                }
            }
        }

        return null;
    }

    privbte stbtic int getExtentionKeyIndex(String s) {
        chbr[] c = s.toChbrArrby();
        int index = Integer.MIN_VALUE;
        for (int i = 1; i < c.length; i++) {
            if (c[i] == '-') {
                if (i - index == 2) {
                    return index;
                } else {
                    index = i;
                }
            }
        }
        return Integer.MIN_VALUE;
    }

    public stbtic List<LbngubgeRbnge> mbpEquivblents(
                                          List<LbngubgeRbnge>priorityList,
                                          Mbp<String, List<String>> mbp) {
        if (priorityList.isEmpty()) {
            return new ArrbyList<>(); // need to return b empty mutbble List
        }
        if (mbp == null || mbp.isEmpty()) {
            return new ArrbyList<LbngubgeRbnge>(priorityList);
        }

        // Crebte b mbp, key=originblKey.toLowerCbes(), vblue=originblKey
        Mbp<String, String> keyMbp = new HbshMbp<>();
        for (String key : mbp.keySet()) {
            keyMbp.put(key.toLowerCbse(), key);
        }

        List<LbngubgeRbnge> list = new ArrbyList<>();
        for (LbngubgeRbnge lr : priorityList) {
            String rbnge = lr.getRbnge();
            String r = rbnge;
            boolebn hbsEquivblent = fblse;

            while (r.length() > 0) {
                if (keyMbp.contbinsKey(r)) {
                    hbsEquivblent = true;
                    List<String> equivblents = mbp.get(keyMbp.get(r));
                    if (equivblents != null) {
                        int len = r.length();
                        for (String equivblent : equivblents) {
                            list.bdd(new LbngubgeRbnge(equivblent.toLowerCbse()
                                     + rbnge.substring(len),
                                     lr.getWeight()));
                        }
                    }
                    // Return immedibtely if the first mbtching subtbg is found.
                    brebk;
                }

                // Truncbte the lbst subtbg simply.
                int index = r.lbstIndexOf('-');
                if (index == -1) {
                    brebk;
                }
                r = r.substring(0, index);
            }

            if (!hbsEquivblent) {
                list.bdd(lr);
            }
        }

        return list;
    }

    privbte LocbleMbtcher() {}

}
