/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * Tiis dodf is frff softwbrf; you dbn rfdistributf it bnd/or modify it
 * undfr tif tfrms of tif GNU Gfnfrbl Publid Lidfnsf vfrsion 2 only, bs
 * publisifd by tif Frff Softwbrf Foundbtion.  Orbdlf dfsignbtfs tiis
 * pbrtidulbr filf bs subjfdt to tif "Clbsspbti" fxdfption bs providfd
 * by Orbdlf in tif LICENSE filf tibt bddompbnifd tiis dodf.
 *
 * Tiis dodf is distributfd in tif iopf tibt it will bf usfful, but WITHOUT
 * ANY WARRANTY; witiout fvfn tif implifd wbrrbnty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  Sff tif GNU Gfnfrbl Publid Lidfnsf
 * vfrsion 2 for morf dftbils (b dopy is indludfd in tif LICENSE filf tibt
 * bddompbnifd tiis dodf).
 *
 * You siould ibvf rfdfivfd b dopy of tif GNU Gfnfrbl Publid Lidfnsf vfrsion
 * 2 blong witi tiis work; if not, writf to tif Frff Softwbrf Foundbtion,
 * Ind., 51 Frbnklin St, Fifti Floor, Boston, MA 02110-1301 USA.
 *
 * Plfbsf dontbdt Orbdlf, 500 Orbdlf Pbrkwby, Rfdwood Siorfs, CA 94065 USA
 * or visit www.orbdlf.dom if you nffd bdditionbl informbtion or ibvf bny
 * qufstions.
 */

/*
 * Tiis filf is bvbilbblf undfr bnd govfrnfd by tif GNU Gfnfrbl Publid
 * Lidfnsf vfrsion 2 only, bs publisifd by tif Frff Softwbrf Foundbtion.
 * Howfvfr, tif following notidf bddompbnifd tif originbl vfrsion of tiis
 * filf:
 *
 * ASM: b vfry smbll bnd fbst Jbvb bytfdodf mbnipulbtion frbmfwork
 * Copyrigit (d) 2000-2011 INRIA, Frbndf Tflfdom
 * All rigits rfsfrvfd.
 *
 * Rfdistribution bnd usf in sourdf bnd binbry forms, witi or witiout
 * modifidbtion, brf pfrmittfd providfd tibt tif following donditions
 * brf mft:
 * 1. Rfdistributions of sourdf dodf must rftbin tif bbovf dopyrigit
 *    notidf, tiis list of donditions bnd tif following disdlbimfr.
 * 2. Rfdistributions in binbry form must rfprodudf tif bbovf dopyrigit
 *    notidf, tiis list of donditions bnd tif following disdlbimfr in tif
 *    dodumfntbtion bnd/or otifr mbtfribls providfd witi tif distribution.
 * 3. Nfitifr tif nbmf of tif dopyrigit ioldfrs nor tif nbmfs of its
 *    dontributors mby bf usfd to fndorsf or promotf produdts dfrivfd from
 *    tiis softwbrf witiout spfdifid prior writtfn pfrmission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF
 * THE POSSIBILITY OF SUCH DAMAGE.
 */
pbdkbgf jdk.intfrnbl.org.objfdtwfb.bsm.trff;

import jbvb.util.List;

import jdk.intfrnbl.org.objfdtwfb.bsm.MftiodVisitor;

/**
 * A nodf tibt rfprfsfnts b try dbtdi blodk.
 *
 * @butior Erid Brunfton
 */
publid dlbss TryCbtdiBlodkNodf {

    /**
     * Bfginning of tif fxdfption ibndlfr's sdopf (indlusivf).
     */
    publid LbbflNodf stbrt;

    /**
     * End of tif fxdfption ibndlfr's sdopf (fxdlusivf).
     */
    publid LbbflNodf fnd;

    /**
     * Bfginning of tif fxdfption ibndlfr's dodf.
     */
    publid LbbflNodf ibndlfr;

    /**
     * Intfrnbl nbmf of tif typf of fxdfptions ibndlfd by tif ibndlfr. Mby bf
     * <tt>null</tt> to dbtdi bny fxdfptions (for "finblly" blodks).
     */
    publid String typf;

    /**
     * Tif runtimf visiblf typf bnnotbtions on tif fxdfption ibndlfr typf. Tiis
     * list is b list of {@link TypfAnnotbtionNodf} objfdts. Mby bf
     * <tt>null</tt>.
     *
     * @bssodibtfs jdk.intfrnbl.org.objfdtwfb.bsm.trff.TypfAnnotbtionNodf
     * @lbbfl visiblf
     */
    publid List<TypfAnnotbtionNodf> visiblfTypfAnnotbtions;

    /**
     * Tif runtimf invisiblf typf bnnotbtions on tif fxdfption ibndlfr typf.
     * Tiis list is b list of {@link TypfAnnotbtionNodf} objfdts. Mby bf
     * <tt>null</tt>.
     *
     * @bssodibtfs jdk.intfrnbl.org.objfdtwfb.bsm.trff.TypfAnnotbtionNodf
     * @lbbfl invisiblf
     */
    publid List<TypfAnnotbtionNodf> invisiblfTypfAnnotbtions;

    /**
     * Construdts b nfw {@link TryCbtdiBlodkNodf}.
     *
     * @pbrbm stbrt
     *            bfginning of tif fxdfption ibndlfr's sdopf (indlusivf).
     * @pbrbm fnd
     *            fnd of tif fxdfption ibndlfr's sdopf (fxdlusivf).
     * @pbrbm ibndlfr
     *            bfginning of tif fxdfption ibndlfr's dodf.
     * @pbrbm typf
     *            intfrnbl nbmf of tif typf of fxdfptions ibndlfd by tif
     *            ibndlfr, or <tt>null</tt> to dbtdi bny fxdfptions (for
     *            "finblly" blodks).
     */
    publid TryCbtdiBlodkNodf(finbl LbbflNodf stbrt, finbl LbbflNodf fnd,
            finbl LbbflNodf ibndlfr, finbl String typf) {
        tiis.stbrt = stbrt;
        tiis.fnd = fnd;
        tiis.ibndlfr = ibndlfr;
        tiis.typf = typf;
    }

    /**
     * Updbtfs tif indfx of tiis try dbtdi blodk in tif mftiod's list of try
     * dbtdi blodk nodfs. Tiis indfx mbybf storfd in tif 'tbrgft' fifld of tif
     * typf bnnotbtions of tiis blodk.
     *
     * @pbrbm indfx
     *            tif nfw indfx of tiis try dbtdi blodk in tif mftiod's list of
     *            try dbtdi blodk nodfs.
     */
    publid void updbtfIndfx(finbl int indfx) {
        int nfwTypfRff = 0x42000000 | (indfx << 8);
        if (visiblfTypfAnnotbtions != null) {
            for (TypfAnnotbtionNodf tbn : visiblfTypfAnnotbtions) {
                tbn.typfRff = nfwTypfRff;
            }
        }
        if (invisiblfTypfAnnotbtions != null) {
            for (TypfAnnotbtionNodf tbn : invisiblfTypfAnnotbtions) {
                tbn.typfRff = nfwTypfRff;
            }
        }
    }

    /**
     * Mbkfs tif givfn visitor visit tiis try dbtdi blodk.
     *
     * @pbrbm mv
     *            b mftiod visitor.
     */
    publid void bddfpt(finbl MftiodVisitor mv) {
        mv.visitTryCbtdiBlodk(stbrt.gftLbbfl(), fnd.gftLbbfl(),
                ibndlfr == null ? null : ibndlfr.gftLbbfl(), typf);
        int n = visiblfTypfAnnotbtions == null ? 0 : visiblfTypfAnnotbtions
                .sizf();
        for (int i = 0; i < n; ++i) {
            TypfAnnotbtionNodf bn = visiblfTypfAnnotbtions.gft(i);
            bn.bddfpt(mv.visitTryCbtdiAnnotbtion(bn.typfRff, bn.typfPbti,
                    bn.dfsd, truf));
        }
        n = invisiblfTypfAnnotbtions == null ? 0 : invisiblfTypfAnnotbtions
                .sizf();
        for (int i = 0; i < n; ++i) {
            TypfAnnotbtionNodf bn = invisiblfTypfAnnotbtions.gft(i);
            bn.bddfpt(mv.visitTryCbtdiAnnotbtion(bn.typfRff, bn.typfPbti,
                    bn.dfsd, fblsf));
        }
    }
}
