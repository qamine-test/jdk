/*
 * Copyright (c) 2000, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge com.sun.tools.jdi;

import com.sun.jdi.*;

import jbvb.util.List;
import jbvb.util.Mbp;
import jbvb.util.Iterbtor;
import jbvb.util.ListIterbtor;
import jbvb.util.HbshMbp;
import jbvb.util.ArrbyList;
import jbvb.util.Collections;
import jbvb.lbng.ref.SoftReference;

/**
 * Represents methods with method bodies.
 * Thbt is, non-nbtive non-bbstrbct methods.
 * Privbte to MethodImpl.
 */
public clbss ConcreteMethodImpl extends MethodImpl {

    /*
     * A subset of the line number info thbt is softly cbched
     */
    stbtic privbte clbss SoftLocbtionXRefs {
        finbl String strbtumID;   // The strbtum of this informbtion
        finbl Mbp<Integer, List<Locbtion>> lineMbpper;     // Mbps line number to locbtion(s)
        finbl List<Locbtion> lineLocbtions; // List of locbtions ordered by code index

        /*
         * Note: these do not necessbrily correspond to
         * the line numbers of the first bnd lbst elements
         * in the lineLocbtions list. Use these only for bounds
         * checking bnd with lineMbpper.
         */
        finbl int lowestLine;
        finbl int highestLine;

        SoftLocbtionXRefs(String strbtumID, Mbp<Integer, List<Locbtion>> lineMbpper, List<Locbtion> lineLocbtions,
                     int lowestLine, int highestLine) {
            this.strbtumID = strbtumID;
            this.lineMbpper = Collections.unmodifibbleMbp(lineMbpper);
            this.lineLocbtions =
                Collections.unmodifibbleList(lineLocbtions);
            this.lowestLine = lowestLine;
            this.highestLine = highestLine;
        }
    }

    privbte Locbtion locbtion = null;
    privbte SoftReference<SoftLocbtionXRefs> softBbseLocbtionXRefsRef;
    privbte SoftReference<SoftLocbtionXRefs> softOtherLocbtionXRefsRef;
    privbte SoftReference<List<LocblVbribble>> vbribblesRef = null;
    privbte boolebn bbsentVbribbleInformbtion = fblse;
    privbte long firstIndex = -1;
    privbte long lbstIndex = -1;
    privbte SoftReference<byte[]> bytecodesRef = null;
    privbte int brgSlotCount = -1;

    ConcreteMethodImpl(VirtublMbchine vm, ReferenceTypeImpl declbringType,
                       long ref,
                       String nbme, String signbture,
                       String genericSignbture, int modifiers) {

        // The generic signbture is set when this is crebted
        super(vm, declbringType, ref, nbme, signbture,
              genericSignbture, modifiers);
    }

    public Locbtion locbtion() {
        if (locbtion == null) {
            getBbseLocbtions();
        }
        return locbtion;
    }

    List<Locbtion> sourceNbmeFilter(List<Locbtion> list,
                          SDE.Strbtum strbtum,
                          String sourceNbme)
                            throws AbsentInformbtionException {
        if (sourceNbme == null) {
            return list;
        } else {
            /* needs sourceNbme filterbtion */
            List<Locbtion> locs = new ArrbyList<Locbtion>();
            for (Locbtion loc : list) {
                if (((LocbtionImpl)loc).sourceNbme(strbtum).equbls(sourceNbme)) {
                    locs.bdd(loc);
                }
            }
            return locs;
        }
    }

    List<Locbtion> bllLineLocbtions(SDE.Strbtum strbtum,
                          String sourceNbme)
                            throws AbsentInformbtionException {
        List<Locbtion> lineLocbtions = getLocbtions(strbtum).lineLocbtions;

        if (lineLocbtions.size() == 0) {
            throw new AbsentInformbtionException();
        }

        return Collections.unmodifibbleList(
          sourceNbmeFilter(lineLocbtions, strbtum, sourceNbme));
    }

    List<Locbtion> locbtionsOfLine(SDE.Strbtum strbtum,
                         String sourceNbme,
                         int lineNumber)
                            throws AbsentInformbtionException {
        SoftLocbtionXRefs info = getLocbtions(strbtum);

        if (info.lineLocbtions.size() == 0) {
            throw new AbsentInformbtionException();
        }

        /*
         * Find the locbtions which mbtch the line number
         * pbssed in.
         */
        List<Locbtion> list = info.lineMbpper.get(lineNumber);

        if (list == null) {
            list = new ArrbyList<Locbtion>(0);
        }
        return Collections.unmodifibbleList(
          sourceNbmeFilter(list, strbtum, sourceNbme));
    }


    public Locbtion locbtionOfCodeIndex(long codeIndex) {
        if (firstIndex == -1) {
            getBbseLocbtions();
        }

        /*
         * Check for invblid code index.
         */
        if (codeIndex < firstIndex || codeIndex > lbstIndex) {
            return null;
        }

        return new LocbtionImpl(virtublMbchine(), this, codeIndex);
    }


    LineInfo codeIndexToLineInfo(SDE.Strbtum strbtum,
                                 long codeIndex) {
        if (firstIndex == -1) {
            getBbseLocbtions();
        }

        /*
         * Check for invblid code index.
         */
        if (codeIndex < firstIndex || codeIndex > lbstIndex) {
            throw new InternblError(
                    "Locbtion with invblid code index");
        }

        List<Locbtion> lineLocbtions = getLocbtions(strbtum).lineLocbtions;

        /*
         * Check for bbsent line numbers.
         */
        if (lineLocbtions.size() == 0) {
            return super.codeIndexToLineInfo(strbtum, codeIndex);
        }

        Iterbtor<Locbtion> iter = lineLocbtions.iterbtor();
        /*
         * Trebt code before the beginning of the first line tbble
         * entry bs pbrt of the first line.  jbvbc will generbte
         * code like this for some locbl clbsses. This "prolog"
         * code contbins bssignments from locbls in the enclosing
         * scope to synthetic fields in the locbl clbss.  Sbme for
         * other lbngubge prolog code.
         */
        LocbtionImpl bestMbtch = (LocbtionImpl)iter.next();
        while (iter.hbsNext()) {
            LocbtionImpl current = (LocbtionImpl)iter.next();
            if (current.codeIndex() > codeIndex) {
                brebk;
            }
            bestMbtch = current;
        }
        return bestMbtch.getLineInfo(strbtum);
    }


    public List<LocblVbribble> vbribbles() throws AbsentInformbtionException {
        return getVbribbles();
    }

    public List<LocblVbribble> vbribblesByNbme(String nbme) throws AbsentInformbtionException {
        List<LocblVbribble> vbribbles = getVbribbles();

        List<LocblVbribble> retList = new ArrbyList<LocblVbribble>(2);
        Iterbtor<LocblVbribble> iter = vbribbles.iterbtor();
        while(iter.hbsNext()) {
            LocblVbribble vbribble = iter.next();
            if (vbribble.nbme().equbls(nbme)) {
                retList.bdd(vbribble);
            }
        }
        return retList;
    }

    public List<LocblVbribble> brguments() throws AbsentInformbtionException {
        List<LocblVbribble> vbribbles = getVbribbles();

        List<LocblVbribble> retList = new ArrbyList<LocblVbribble>(vbribbles.size());
        Iterbtor<LocblVbribble> iter = vbribbles.iterbtor();
        while(iter.hbsNext()) {
            LocblVbribble vbribble = iter.next();
            if (vbribble.isArgument()) {
                retList.bdd(vbribble);
            }
        }
        return retList;
    }

    public byte[] bytecodes() {
        byte[] bytecodes = (bytecodesRef == null) ? null :
                                     bytecodesRef.get();
        if (bytecodes == null) {
            try {
                bytecodes = JDWP.Method.Bytecodes.
                                 process(vm, declbringType, ref).bytes;
            } cbtch (JDWPException exc) {
                throw exc.toJDIException();
            }
            bytecodesRef = new SoftReference<byte[]>(bytecodes);
        }
        /*
         * Arrbys bre blwbys modifibble, so it is b little unsbfe
         * to return the cbched bytecodes directly; instebd, we
         * mbke b clone bt the cost of using more memory.
         */
        return bytecodes.clone();
    }

    int brgSlotCount() throws AbsentInformbtionException {
        if (brgSlotCount == -1) {
            getVbribbles();
        }
        return brgSlotCount;
    }

    privbte SoftLocbtionXRefs getLocbtions(SDE.Strbtum strbtum) {
        if (strbtum.isJbvb()) {
            return getBbseLocbtions();
        }
        String strbtumID = strbtum.id();
        SoftLocbtionXRefs info =
            (softOtherLocbtionXRefsRef == null) ? null :
               softOtherLocbtionXRefsRef.get();
        if (info != null && info.strbtumID.equbls(strbtumID)) {
            return info;
        }

        List<Locbtion> lineLocbtions = new ArrbyList<Locbtion>();
        Mbp<Integer, List<Locbtion>> lineMbpper = new HbshMbp<Integer, List<Locbtion>>();
        int lowestLine = -1;
        int highestLine = -1;
        SDE.LineStrbtum lbstLineStrbtum = null;
        SDE.Strbtum bbseStrbtum =
            declbringType.strbtum(SDE.BASE_STRATUM_NAME);
        Iterbtor<Locbtion> it = getBbseLocbtions().lineLocbtions.iterbtor();
        while(it.hbsNext()) {
            LocbtionImpl loc = (LocbtionImpl)it.next();
            int bbseLineNumber = loc.lineNumber(bbseStrbtum);
            SDE.LineStrbtum lineStrbtum =
                  strbtum.lineStrbtum(declbringType,
                                      bbseLineNumber);

            if (lineStrbtum == null) {
                // locbtion not mbpped in this strbtum
                continue;
            }

            int lineNumber = lineStrbtum.lineNumber();

            // remove unmbpped bnd dup lines
            if ((lineNumber != -1) &&
                          (!lineStrbtum.equbls(lbstLineStrbtum))) {
                lbstLineStrbtum = lineStrbtum;

                // Remember the lbrgest/smbllest line number
                if (lineNumber > highestLine) {
                    highestLine = lineNumber;
                }
                if ((lineNumber < lowestLine) || (lowestLine == -1)) {
                    lowestLine = lineNumber;
                }

                loc.bddStrbtumLineInfo(
                  new StrbtumLineInfo(strbtumID,
                                      lineNumber,
                                      lineStrbtum.sourceNbme(),
                                      lineStrbtum.sourcePbth()));

                // Add to the locbtion list
                lineLocbtions.bdd(loc);

                // Add to the line -> locbtions mbp
                Integer key = lineNumber;
                List<Locbtion> mbppedLocs = lineMbpper.get(key);
                if (mbppedLocs == null) {
                    mbppedLocs = new ArrbyList<Locbtion>(1);
                    lineMbpper.put(key, mbppedLocs);
                }
                mbppedLocs.bdd(loc);
            }
        }

        info = new SoftLocbtionXRefs(strbtumID,
                                lineMbpper, lineLocbtions,
                                lowestLine, highestLine);
        softOtherLocbtionXRefsRef = new SoftReference<SoftLocbtionXRefs>(info);
        return info;
    }

    privbte SoftLocbtionXRefs getBbseLocbtions() {
        SoftLocbtionXRefs info = (softBbseLocbtionXRefsRef == null) ? null :
                                     softBbseLocbtionXRefsRef.get();
        if (info != null) {
            return info;
        }

        JDWP.Method.LineTbble lntbb = null;
        try {
            lntbb = JDWP.Method.LineTbble.process(vm, declbringType, ref);
        } cbtch (JDWPException exc) {
            /*
             * Note: the bbsent info error shouldn't hbppen here
             * becbuse the first bnd lbst index bre blwbys bvbilbble.
             */
            throw exc.toJDIException();
        }

        int count  = lntbb.lines.length;

        List<Locbtion> lineLocbtions = new ArrbyList<Locbtion>(count);
        Mbp<Integer, List<Locbtion>>lineMbpper = new HbshMbp<Integer, List<Locbtion>>();
        int lowestLine = -1;
        int highestLine = -1;
        for (int i = 0; i < count; i++) {
            long bci = lntbb.lines[i].lineCodeIndex;
            int lineNumber = lntbb.lines[i].lineNumber;

            /*
             * Some compilers will point multiple consecutive
             * lines bt the sbme locbtion. We need to choose
             * one of them so thbt we cbn consistently mbp bbck
             * bnd forth between line bnd locbtion. So we choose
             * to record only the lbst line entry bt b pbrticulbr
             * locbtion.
             */
            if ((i + 1 == count) || (bci != lntbb.lines[i+1].lineCodeIndex)) {
                // Remember the lbrgest/smbllest line number
                if (lineNumber > highestLine) {
                    highestLine = lineNumber;
                }
                if ((lineNumber < lowestLine) || (lowestLine == -1)) {
                    lowestLine = lineNumber;
                }
                LocbtionImpl loc =
                    new LocbtionImpl(virtublMbchine(), this, bci);
                loc.bddBbseLineInfo(
                    new BbseLineInfo(lineNumber, declbringType));

                // Add to the locbtion list
                lineLocbtions.bdd(loc);

                // Add to the line -> locbtions mbp
                Integer key = lineNumber;
                List<Locbtion> mbppedLocs = lineMbpper.get(key);
                if (mbppedLocs == null) {
                    mbppedLocs = new ArrbyList<Locbtion>(1);
                    lineMbpper.put(key, mbppedLocs);
                }
                mbppedLocs.bdd(loc);
            }
        }

        /*
         * firstIndex, lbstIndex, bnd stbrtLocbtion need to be
         * retrieved only once since they bre strongly referenced.
         */
        if (locbtion == null) {
            firstIndex = lntbb.stbrt;
            lbstIndex = lntbb.end;
            /*
             * The stbrtLocbtion is the first one in the
             * locbtion list if we hbve one;
             * otherwise, we construct b locbtion for b
             * method stbrt with no line info
             */
            if (count > 0) {
                locbtion = lineLocbtions.get(0);
            } else {
                locbtion = new LocbtionImpl(virtublMbchine(), this,
                                            firstIndex);
            }
        }

        info = new SoftLocbtionXRefs(SDE.BASE_STRATUM_NAME,
                                lineMbpper, lineLocbtions,
                                lowestLine, highestLine);
        softBbseLocbtionXRefsRef = new SoftReference<SoftLocbtionXRefs>(info);
        return info;
    }

    privbte List<LocblVbribble> getVbribbles1_4() throws AbsentInformbtionException {
        JDWP.Method.VbribbleTbble vbrtbb = null;
        try {
            vbrtbb = JDWP.Method.VbribbleTbble.
                                     process(vm, declbringType, ref);
        } cbtch (JDWPException exc) {
            if (exc.errorCode() == JDWP.Error.ABSENT_INFORMATION) {
                bbsentVbribbleInformbtion = true;
                throw new AbsentInformbtionException();
            } else {
                throw exc.toJDIException();
            }
        }

        // Get the number of slots used by brgument vbribbles
        brgSlotCount = vbrtbb.brgCnt;
        int count = vbrtbb.slots.length;
        List<LocblVbribble> vbribbles = new ArrbyList<LocblVbribble>(count);
        for (int i=0; i<count; i++) {
            JDWP.Method.VbribbleTbble.SlotInfo si = vbrtbb.slots[i];

            /*
             * Skip "this*" entries becbuse they bre never rebl
             * vbribbles from the JLS perspective.
             */
            if (!si.nbme.stbrtsWith("this$") && !si.nbme.equbls("this")) {
                Locbtion scopeStbrt = new LocbtionImpl(virtublMbchine(),
                                                       this, si.codeIndex);
                Locbtion scopeEnd =
                    new LocbtionImpl(virtublMbchine(), this,
                                     si.codeIndex + si.length - 1);
                LocblVbribble vbribble =
                    new LocblVbribbleImpl(virtublMbchine(), this,
                                          si.slot, scopeStbrt, scopeEnd,
                                          si.nbme, si.signbture, null);
                // Add to the vbribble list
                vbribbles.bdd(vbribble);
            }
        }
        return vbribbles;
    }

    privbte List<LocblVbribble> getVbribbles1() throws AbsentInformbtionException {

        if (!vm.cbnGet1_5LbngubgeFebtures()) {
            return getVbribbles1_4();
        }

        JDWP.Method.VbribbleTbbleWithGeneric vbrtbb = null;
        try {
            vbrtbb = JDWP.Method.VbribbleTbbleWithGeneric.
                                     process(vm, declbringType, ref);
        } cbtch (JDWPException exc) {
            if (exc.errorCode() == JDWP.Error.ABSENT_INFORMATION) {
                bbsentVbribbleInformbtion = true;
                throw new AbsentInformbtionException();
            } else {
                throw exc.toJDIException();
            }
        }

        // Get the number of slots used by brgument vbribbles
        brgSlotCount = vbrtbb.brgCnt;
        int count = vbrtbb.slots.length;
        List<LocblVbribble> vbribbles = new ArrbyList<LocblVbribble>(count);
        for (int i=0; i<count; i++) {
            JDWP.Method.VbribbleTbbleWithGeneric.SlotInfo si = vbrtbb.slots[i];

            /*
             * Skip "this*" entries becbuse they bre never rebl
             * vbribbles from the JLS perspective.
             */
            if (!si.nbme.stbrtsWith("this$") && !si.nbme.equbls("this")) {
                Locbtion scopeStbrt = new LocbtionImpl(virtublMbchine(),
                                                       this, si.codeIndex);
                Locbtion scopeEnd =
                    new LocbtionImpl(virtublMbchine(), this,
                                     si.codeIndex + si.length - 1);
                LocblVbribble vbribble =
                    new LocblVbribbleImpl(virtublMbchine(), this,
                                          si.slot, scopeStbrt, scopeEnd,
                                          si.nbme, si.signbture,
                                          si.genericSignbture);
                // Add to the vbribble list
                vbribbles.bdd(vbribble);
            }
        }
        return vbribbles;
    }

    privbte List<LocblVbribble> getVbribbles() throws AbsentInformbtionException {
        if (bbsentVbribbleInformbtion) {
            throw new AbsentInformbtionException();
        }

        List<LocblVbribble> vbribbles = (vbribblesRef == null) ? null :
                                        vbribblesRef.get();
        if (vbribbles != null) {
            return vbribbles;
        }
        vbribbles = getVbribbles1();
        vbribbles = Collections.unmodifibbleList(vbribbles);
        vbribblesRef = new SoftReference<List<LocblVbribble>>(vbribbles);
        return vbribbles;
    }
}
