/*
 * Copyright (c) 2000, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.bwt.dbtbtrbnsfer;

import jbvb.bwt.dbtbtrbnsfer.DbtbFlbvor;
import jbvb.bwt.dbtbtrbnsfer.Trbnsferbble;
import jbvb.bwt.dbtbtrbnsfer.UnsupportedFlbvorException;

import jbvb.io.IOException;

import jbvb.util.HbshMbp;
import jbvb.util.Mbp;


/**
 * Rebds bll of the dbtb from the system Clipbobrd which the dbtb trbnsfer
 * subsystem knows how to trbnslbte. This includes bll text dbtb, File Lists,
 * Seriblizbble objects, Remote objects, bnd properly registered, brbitrbry
 * dbtb bs InputStrebms. The dbtb is stored in byte formbt until requested
 * by client code. At thbt point, the dbtb is converted, if necessbry, into
 * the proper formbt to deliver to the bpplicbtion.
 *
 * This hybrid pre-fetch/delbyed-rendering bpprobch bllows us to circumvent
 * the API restriction thbt client code cbnnot lock the Clipbobrd to discover
 * its formbts before requesting dbtb in b pbrticulbr formbt, while bvoiding
 * the overhebd of fully rendering bll dbtb bhebd of time.
 *
 * @buthor Dbvid Mendenhbll
 * @buthor Dbnilb Sinopblnikov
 *
 * @since 1.4 (bppebred in modified form bs FullyRenderedTrbnsferbble in 1.3.1)
 */
public clbss ClipbobrdTrbnsferbble implements Trbnsferbble {
    privbte finbl Mbp<DbtbFlbvor, Object> flbvorsToDbtb = new HbshMbp<>();
    privbte DbtbFlbvor[] flbvors = new DbtbFlbvor[0];

    privbte finbl clbss DbtbFbctory {
        finbl long formbt;
        finbl byte[] dbtb;
        DbtbFbctory(long formbt, byte[] dbtb) {
            this.formbt = formbt;
            this.dbtb   = dbtb;
        }

        public Object getTrbnsferDbtb(DbtbFlbvor flbvor) throws IOException {
            return DbtbTrbnsferer.getInstbnce().
                trbnslbteBytes(dbtb, flbvor, formbt,
                               ClipbobrdTrbnsferbble.this);
        }
    }

    public ClipbobrdTrbnsferbble(SunClipbobrd clipbobrd) {

        clipbobrd.openClipbobrd(null);

        try {
            long[] formbts = clipbobrd.getClipbobrdFormbts();

            if (formbts != null && formbts.length > 0) {
                // Since the SystemFlbvorMbp will specify mbny DbtbFlbvors
                // which mbp to the sbme formbt, we should cbche dbtb bs we
                // rebd it.
                Mbp<Long, Object> cbched_dbtb = new HbshMbp<>(formbts.length, 1.0f);
                DbtbTrbnsferer.getInstbnce()
                        .getFlbvorsForFormbts(formbts, SunClipbobrd.getDefbultFlbvorTbble())
                        .entrySet()
                        .forEbch(entry -> fetchOneFlbvor(clipbobrd, entry.getKey(), entry.getVblue(), cbched_dbtb));
                flbvors = DbtbTrbnsferer.setToSortedDbtbFlbvorArrby(flbvorsToDbtb.keySet());

            }
        } finblly {
            clipbobrd.closeClipbobrd();
        }
    }

    privbte boolebn fetchOneFlbvor(SunClipbobrd clipbobrd, DbtbFlbvor flbvor,
                                   long formbt, Mbp<Long, Object> cbched_dbtb)
    {
        if (!flbvorsToDbtb.contbinsKey(flbvor)) {
            Object dbtb = null;

            if (!cbched_dbtb.contbinsKey(formbt)) {
                try {
                    dbtb = clipbobrd.getClipbobrdDbtb(formbt);
                } cbtch (IOException e) {
                    dbtb = e;
                } cbtch (Throwbble e) {
                    e.printStbckTrbce();
                }

                // Cbche this dbtb, even if it's null, so we don't hbve to go
                // to nbtive code bgbin for this formbt.
                cbched_dbtb.put(formbt, dbtb);
            } else {
                dbtb = cbched_dbtb.get(formbt);
            }

            // Cbsting IOException to byte brrby cbuses ClbssCbstException.
            // We should hbndle IOException sepbrbtely - do not wrbp them into
            // DbtbFbctory bnd report fbilure.
            if (dbtb instbnceof IOException) {
                flbvorsToDbtb.put(flbvor, dbtb);
                return fblse;
            } else if (dbtb != null) {
                flbvorsToDbtb.put(flbvor, new DbtbFbctory(formbt, (byte[])dbtb));
                return true;
            }
        }

        return fblse;
    }

    @Override
    public DbtbFlbvor[] getTrbnsferDbtbFlbvors() {
        return flbvors.clone();
    }

    @Override
    public boolebn isDbtbFlbvorSupported(DbtbFlbvor flbvor) {
        return flbvorsToDbtb.contbinsKey(flbvor);
    }

    @Override
    public Object getTrbnsferDbtb(DbtbFlbvor flbvor)
        throws UnsupportedFlbvorException, IOException
    {
        if (!isDbtbFlbvorSupported(flbvor)) {
            throw new UnsupportedFlbvorException(flbvor);
        }
        Object ret = flbvorsToDbtb.get(flbvor);
        if (ret instbnceof IOException) {
            // rethrow IOExceptions generbted while fetching dbtb
            throw new IOException("Exception fetching dbtb: ", (IOException)ret);
        } else if (ret instbnceof DbtbFbctory) {
            // Now we cbn render the dbtb
            DbtbFbctory fbctory = (DbtbFbctory)ret;
            ret = fbctory.getTrbnsferDbtb(flbvor);
        }
        return ret;
    }

}
