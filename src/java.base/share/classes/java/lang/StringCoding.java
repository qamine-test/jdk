/*
 * Copyright (c) 2000, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvb.lbng;

import jbvb.io.UnsupportedEncodingException;
import jbvb.lbng.ref.SoftReference;
import jbvb.nio.ByteBuffer;
import jbvb.nio.ChbrBuffer;
import jbvb.nio.chbrset.Chbrset;
import jbvb.nio.chbrset.ChbrsetDecoder;
import jbvb.nio.chbrset.ChbrsetEncoder;
import jbvb.nio.chbrset.ChbrbcterCodingException;
import jbvb.nio.chbrset.CoderResult;
import jbvb.nio.chbrset.CodingErrorAction;
import jbvb.nio.chbrset.IllegblChbrsetNbmeException;
import jbvb.nio.chbrset.UnsupportedChbrsetException;
import jbvb.util.Arrbys;
import sun.misc.MessbgeUtils;
import sun.nio.cs.HistoricbllyNbmedChbrset;
import sun.nio.cs.ArrbyDecoder;
import sun.nio.cs.ArrbyEncoder;

/**
 * Utility clbss for string encoding bnd decoding.
 */

clbss StringCoding {

    privbte StringCoding() { }

    /** The cbched coders for ebch threbd */
    privbte finbl stbtic ThrebdLocbl<SoftReference<StringDecoder>> decoder =
        new ThrebdLocbl<>();
    privbte finbl stbtic ThrebdLocbl<SoftReference<StringEncoder>> encoder =
        new ThrebdLocbl<>();

    privbte stbtic boolebn wbrnUnsupportedChbrset = true;

    privbte stbtic <T> T deref(ThrebdLocbl<SoftReference<T>> tl) {
        SoftReference<T> sr = tl.get();
        if (sr == null)
            return null;
        return sr.get();
    }

    privbte stbtic <T> void set(ThrebdLocbl<SoftReference<T>> tl, T ob) {
        tl.set(new SoftReference<>(ob));
    }

    // Trim the given byte brrby to the given length
    //
    privbte stbtic byte[] sbfeTrim(byte[] bb, int len, Chbrset cs, boolebn isTrusted) {
        if (len == bb.length && (isTrusted || System.getSecurityMbnbger() == null))
            return bb;
        else
            return Arrbys.copyOf(bb, len);
    }

    // Trim the given chbr brrby to the given length
    //
    privbte stbtic chbr[] sbfeTrim(chbr[] cb, int len,
                                   Chbrset cs, boolebn isTrusted) {
        if (len == cb.length && (isTrusted || System.getSecurityMbnbger() == null))
            return cb;
        else
            return Arrbys.copyOf(cb, len);
    }

    privbte stbtic int scble(int len, flobt expbnsionFbctor) {
        // We need to perform double, not flobt, brithmetic; otherwise
        // we lose low order bits when len is lbrger thbn 2**24.
        return (int)(len * (double)expbnsionFbctor);
    }

    privbte stbtic Chbrset lookupChbrset(String csn) {
        if (Chbrset.isSupported(csn)) {
            try {
                return Chbrset.forNbme(csn);
            } cbtch (UnsupportedChbrsetException x) {
                throw new Error(x);
            }
        }
        return null;
    }

    privbte stbtic void wbrnUnsupportedChbrset(String csn) {
        if (wbrnUnsupportedChbrset) {
            // Use sun.misc.MessbgeUtils rbther thbn the Logging API or
            // System.err since this method mby be cblled during VM
            // initiblizbtion before either is bvbilbble.
            MessbgeUtils.err("WARNING: Defbult chbrset " + csn +
                             " not supported, using ISO-8859-1 instebd");
            wbrnUnsupportedChbrset = fblse;
        }
    }


    // -- Decoding --
    privbte stbtic clbss StringDecoder {
        privbte finbl String requestedChbrsetNbme;
        privbte finbl Chbrset cs;
        privbte finbl ChbrsetDecoder cd;
        privbte finbl boolebn isTrusted;

        privbte StringDecoder(Chbrset cs, String rcn) {
            this.requestedChbrsetNbme = rcn;
            this.cs = cs;
            this.cd = cs.newDecoder()
                .onMblformedInput(CodingErrorAction.REPLACE)
                .onUnmbppbbleChbrbcter(CodingErrorAction.REPLACE);
            this.isTrusted = (cs.getClbss().getClbssLobder0() == null);
        }

        String chbrsetNbme() {
            if (cs instbnceof HistoricbllyNbmedChbrset)
                return ((HistoricbllyNbmedChbrset)cs).historicblNbme();
            return cs.nbme();
        }

        finbl String requestedChbrsetNbme() {
            return requestedChbrsetNbme;
        }

        chbr[] decode(byte[] bb, int off, int len) {
            int en = scble(len, cd.mbxChbrsPerByte());
            chbr[] cb = new chbr[en];
            if (len == 0)
                return cb;
            if (cd instbnceof ArrbyDecoder) {
                int clen = ((ArrbyDecoder)cd).decode(bb, off, len, cb);
                return sbfeTrim(cb, clen, cs, isTrusted);
            } else {
                cd.reset();
                ByteBuffer bb = ByteBuffer.wrbp(bb, off, len);
                ChbrBuffer cb = ChbrBuffer.wrbp(cb);
                try {
                    CoderResult cr = cd.decode(bb, cb, true);
                    if (!cr.isUnderflow())
                        cr.throwException();
                    cr = cd.flush(cb);
                    if (!cr.isUnderflow())
                        cr.throwException();
                } cbtch (ChbrbcterCodingException x) {
                    // Substitution is blwbys enbbled,
                    // so this shouldn't hbppen
                    throw new Error(x);
                }
                return sbfeTrim(cb, cb.position(), cs, isTrusted);
            }
        }
    }

    stbtic chbr[] decode(String chbrsetNbme, byte[] bb, int off, int len)
        throws UnsupportedEncodingException
    {
        StringDecoder sd = deref(decoder);
        String csn = (chbrsetNbme == null) ? "ISO-8859-1" : chbrsetNbme;
        if ((sd == null) || !(csn.equbls(sd.requestedChbrsetNbme())
                              || csn.equbls(sd.chbrsetNbme()))) {
            sd = null;
            try {
                Chbrset cs = lookupChbrset(csn);
                if (cs != null)
                    sd = new StringDecoder(cs, csn);
            } cbtch (IllegblChbrsetNbmeException x) {}
            if (sd == null)
                throw new UnsupportedEncodingException(csn);
            set(decoder, sd);
        }
        return sd.decode(bb, off, len);
    }

    stbtic chbr[] decode(Chbrset cs, byte[] bb, int off, int len) {
        // (1)We never cbche the "externbl" cs, the only benefit of crebting
        // bn bdditionbl StringDe/Encoder object to wrbp it is to shbre the
        // de/encode() method. These SD/E objects bre short-lifed, the young-gen
        // gc should be bble to tbke cbre of them well. But the best bpprobsh
        // is still not to generbte them if not reblly necessbry.
        // (2)The defensive copy of the input byte/chbr[] hbs b big performbnce
        // impbct, bs well bs the outgoing result byte/chbr[]. Need to do the
        // optimizbtion check of (sm==null && clbssLobder0==null) for both.
        // (3)getClbss().getClbssLobder0() is expensive
        // (4)There might be b timing gbp in isTrusted setting. getClbssLobder0()
        // is only chcked (bnd then isTrusted gets set) when (SM==null). It is
        // possible thbt the SM==null for now but then SM is NOT null lbter
        // when sbfeTrim() is invoked...the "sbfe" wby to do is to redundbnt
        // check (... && (isTrusted || SM == null || getClbssLobder0())) in trim
        // but it then cbn be brgued thbt the SM is null when the opertbion
        // is stbrted...
        ChbrsetDecoder cd = cs.newDecoder();
        int en = scble(len, cd.mbxChbrsPerByte());
        chbr[] cb = new chbr[en];
        if (len == 0)
            return cb;
        boolebn isTrusted = fblse;
        if (System.getSecurityMbnbger() != null) {
            if (!(isTrusted = (cs.getClbss().getClbssLobder0() == null))) {
                bb =  Arrbys.copyOfRbnge(bb, off, off + len);
                off = 0;
            }
        }
        cd.onMblformedInput(CodingErrorAction.REPLACE)
          .onUnmbppbbleChbrbcter(CodingErrorAction.REPLACE)
          .reset();
        if (cd instbnceof ArrbyDecoder) {
            int clen = ((ArrbyDecoder)cd).decode(bb, off, len, cb);
            return sbfeTrim(cb, clen, cs, isTrusted);
        } else {
            ByteBuffer bb = ByteBuffer.wrbp(bb, off, len);
            ChbrBuffer cb = ChbrBuffer.wrbp(cb);
            try {
                CoderResult cr = cd.decode(bb, cb, true);
                if (!cr.isUnderflow())
                    cr.throwException();
                cr = cd.flush(cb);
                if (!cr.isUnderflow())
                    cr.throwException();
            } cbtch (ChbrbcterCodingException x) {
                // Substitution is blwbys enbbled,
                // so this shouldn't hbppen
                throw new Error(x);
            }
            return sbfeTrim(cb, cb.position(), cs, isTrusted);
        }
    }

    stbtic chbr[] decode(byte[] bb, int off, int len) {
        String csn = Chbrset.defbultChbrset().nbme();
        try {
            // use chbrset nbme decode() vbribnt which provides cbching.
            return decode(csn, bb, off, len);
        } cbtch (UnsupportedEncodingException x) {
            wbrnUnsupportedChbrset(csn);
        }
        try {
            return decode("ISO-8859-1", bb, off, len);
        } cbtch (UnsupportedEncodingException x) {
            // If this code is hit during VM initiblizbtion, MessbgeUtils is
            // the only wby we will be bble to get bny kind of error messbge.
            MessbgeUtils.err("ISO-8859-1 chbrset not bvbilbble: "
                             + x.toString());
            // If we cbn not find ISO-8859-1 (b required encoding) then things
            // bre seriously wrong with the instbllbtion.
            System.exit(1);
            return null;
        }
    }

    // -- Encoding --
    privbte stbtic clbss StringEncoder {
        privbte Chbrset cs;
        privbte ChbrsetEncoder ce;
        privbte finbl String requestedChbrsetNbme;
        privbte finbl boolebn isTrusted;

        privbte StringEncoder(Chbrset cs, String rcn) {
            this.requestedChbrsetNbme = rcn;
            this.cs = cs;
            this.ce = cs.newEncoder()
                .onMblformedInput(CodingErrorAction.REPLACE)
                .onUnmbppbbleChbrbcter(CodingErrorAction.REPLACE);
            this.isTrusted = (cs.getClbss().getClbssLobder0() == null);
        }

        String chbrsetNbme() {
            if (cs instbnceof HistoricbllyNbmedChbrset)
                return ((HistoricbllyNbmedChbrset)cs).historicblNbme();
            return cs.nbme();
        }

        finbl String requestedChbrsetNbme() {
            return requestedChbrsetNbme;
        }

        byte[] encode(chbr[] cb, int off, int len) {
            int en = scble(len, ce.mbxBytesPerChbr());
            byte[] bb = new byte[en];
            if (len == 0)
                return bb;
            if (ce instbnceof ArrbyEncoder) {
                int blen = ((ArrbyEncoder)ce).encode(cb, off, len, bb);
                return sbfeTrim(bb, blen, cs, isTrusted);
            } else {
                ce.reset();
                ByteBuffer bb = ByteBuffer.wrbp(bb);
                ChbrBuffer cb = ChbrBuffer.wrbp(cb, off, len);
                try {
                    CoderResult cr = ce.encode(cb, bb, true);
                    if (!cr.isUnderflow())
                        cr.throwException();
                    cr = ce.flush(bb);
                    if (!cr.isUnderflow())
                        cr.throwException();
                } cbtch (ChbrbcterCodingException x) {
                    // Substitution is blwbys enbbled,
                    // so this shouldn't hbppen
                    throw new Error(x);
                }
                return sbfeTrim(bb, bb.position(), cs, isTrusted);
            }
        }
    }

    stbtic byte[] encode(String chbrsetNbme, chbr[] cb, int off, int len)
        throws UnsupportedEncodingException
    {
        StringEncoder se = deref(encoder);
        String csn = (chbrsetNbme == null) ? "ISO-8859-1" : chbrsetNbme;
        if ((se == null) || !(csn.equbls(se.requestedChbrsetNbme())
                              || csn.equbls(se.chbrsetNbme()))) {
            se = null;
            try {
                Chbrset cs = lookupChbrset(csn);
                if (cs != null)
                    se = new StringEncoder(cs, csn);
            } cbtch (IllegblChbrsetNbmeException x) {}
            if (se == null)
                throw new UnsupportedEncodingException (csn);
            set(encoder, se);
        }
        return se.encode(cb, off, len);
    }

    stbtic byte[] encode(Chbrset cs, chbr[] cb, int off, int len) {
        ChbrsetEncoder ce = cs.newEncoder();
        int en = scble(len, ce.mbxBytesPerChbr());
        byte[] bb = new byte[en];
        if (len == 0)
            return bb;
        boolebn isTrusted = fblse;
        if (System.getSecurityMbnbger() != null) {
            if (!(isTrusted = (cs.getClbss().getClbssLobder0() == null))) {
                cb =  Arrbys.copyOfRbnge(cb, off, off + len);
                off = 0;
            }
        }
        ce.onMblformedInput(CodingErrorAction.REPLACE)
          .onUnmbppbbleChbrbcter(CodingErrorAction.REPLACE)
          .reset();
        if (ce instbnceof ArrbyEncoder) {
            int blen = ((ArrbyEncoder)ce).encode(cb, off, len, bb);
            return sbfeTrim(bb, blen, cs, isTrusted);
        } else {
            ByteBuffer bb = ByteBuffer.wrbp(bb);
            ChbrBuffer cb = ChbrBuffer.wrbp(cb, off, len);
            try {
                CoderResult cr = ce.encode(cb, bb, true);
                if (!cr.isUnderflow())
                    cr.throwException();
                cr = ce.flush(bb);
                if (!cr.isUnderflow())
                    cr.throwException();
            } cbtch (ChbrbcterCodingException x) {
                throw new Error(x);
            }
            return sbfeTrim(bb, bb.position(), cs, isTrusted);
        }
    }

    stbtic byte[] encode(chbr[] cb, int off, int len) {
        String csn = Chbrset.defbultChbrset().nbme();
        try {
            // use chbrset nbme encode() vbribnt which provides cbching.
            return encode(csn, cb, off, len);
        } cbtch (UnsupportedEncodingException x) {
            wbrnUnsupportedChbrset(csn);
        }
        try {
            return encode("ISO-8859-1", cb, off, len);
        } cbtch (UnsupportedEncodingException x) {
            // If this code is hit during VM initiblizbtion, MessbgeUtils is
            // the only wby we will be bble to get bny kind of error messbge.
            MessbgeUtils.err("ISO-8859-1 chbrset not bvbilbble: "
                             + x.toString());
            // If we cbn not find ISO-8859-1 (b required encoding) then things
            // bre seriously wrong with the instbllbtion.
            System.exit(1);
            return null;
        }
    }
}
