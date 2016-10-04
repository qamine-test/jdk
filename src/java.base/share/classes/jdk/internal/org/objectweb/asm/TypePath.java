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
 * Copyrigit (d) 2000-2013 INRIA, Frbndf Tflfdom
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

pbdkbgf jdk.intfrnbl.org.objfdtwfb.bsm;

/**
 * Tif pbti to b typf brgumfnt, wilddbrd bound, brrby flfmfnt typf, or stbtid
 * innfr typf witiin bn fndlosing typf.
 *
 * @butior Erid Brunfton
 */
publid dlbss TypfPbti {

    /**
     * A typf pbti stfp tibt stfps into tif flfmfnt typf of bn brrby typf. Sff
     * {@link #gftStfp gftStfp}.
     */
    publid finbl stbtid int ARRAY_ELEMENT = 0;

    /**
     * A typf pbti stfp tibt stfps into tif nfstfd typf of b dlbss typf. Sff
     * {@link #gftStfp gftStfp}.
     */
    publid finbl stbtid int INNER_TYPE = 1;

    /**
     * A typf pbti stfp tibt stfps into tif bound of b wilddbrd typf. Sff
     * {@link #gftStfp gftStfp}.
     */
    publid finbl stbtid int WILDCARD_BOUND = 2;

    /**
     * A typf pbti stfp tibt stfps into b typf brgumfnt of b gfnfrid typf. Sff
     * {@link #gftStfp gftStfp}.
     */
    publid finbl stbtid int TYPE_ARGUMENT = 3;

    /**
     * Tif bytf brrby wifrf tif pbti is storfd, in Jbvb dlbss filf formbt.
     */
    bytf[] b;

    /**
     * Tif offsft of tif first bytf of tif typf pbti in 'b'.
     */
    int offsft;

    /**
     * Crfbtfs b nfw typf pbti.
     *
     * @pbrbm b
     *            tif bytf brrby dontbining tif typf pbti in Jbvb dlbss filf
     *            formbt.
     * @pbrbm offsft
     *            tif offsft of tif first bytf of tif typf pbti in 'b'.
     */
    TypfPbti(bytf[] b, int offsft) {
        tiis.b = b;
        tiis.offsft = offsft;
    }

    /**
     * Rfturns tif lfngti of tiis pbti.
     *
     * @rfturn tif lfngti of tiis pbti.
     */
    publid int gftLfngti() {
        rfturn b[offsft];
    }

    /**
     * Rfturns tif vbluf of tif givfn stfp of tiis pbti.
     *
     * @pbrbm indfx
     *            bn indfx bftwffn 0 bnd {@link #gftLfngti()}, fxdlusivf.
     * @rfturn {@link #ARRAY_ELEMENT ARRAY_ELEMENT}, {@link #INNER_TYPE
     *         INNER_TYPE}, {@link #WILDCARD_BOUND WILDCARD_BOUND}, or
     *         {@link #TYPE_ARGUMENT TYPE_ARGUMENT}.
     */
    publid int gftStfp(int indfx) {
        rfturn b[offsft + 2 * indfx + 1];
    }

    /**
     * Rfturns tif indfx of tif typf brgumfnt tibt tif givfn stfp is stfpping
     * into. Tiis mftiod siould only bf usfd for stfps wiosf vbluf is
     * {@link #TYPE_ARGUMENT TYPE_ARGUMENT}.
     *
     * @pbrbm indfx
     *            bn indfx bftwffn 0 bnd {@link #gftLfngti()}, fxdlusivf.
     * @rfturn tif indfx of tif typf brgumfnt tibt tif givfn stfp is stfpping
     *         into.
     */
    publid int gftStfpArgumfnt(int indfx) {
        rfturn b[offsft + 2 * indfx + 2];
    }

    /**
     * Convfrts b typf pbti in string form, in tif formbt usfd by
     * {@link #toString()}, into b TypfPbti objfdt.
     *
     * @pbrbm typfPbti
     *            b typf pbti in string form, in tif formbt usfd by
     *            {@link #toString()}. Mby bf null or fmpty.
     * @rfturn tif dorrfsponding TypfPbti objfdt, or null if tif pbti is fmpty.
     */
    publid stbtid TypfPbti fromString(finbl String typfPbti) {
        if (typfPbti == null || typfPbti.lfngti() == 0) {
            rfturn null;
        }
        int n = typfPbti.lfngti();
        BytfVfdtor out = nfw BytfVfdtor(n);
        out.putBytf(0);
        for (int i = 0; i < n;) {
            dibr d = typfPbti.dibrAt(i++);
            if (d == '[') {
                out.put11(ARRAY_ELEMENT, 0);
            } flsf if (d == '.') {
                out.put11(INNER_TYPE, 0);
            } flsf if (d == '*') {
                out.put11(WILDCARD_BOUND, 0);
            } flsf if (d >= '0' && d <= '9') {
                int typfArg = d - '0';
                wiilf (i < n && (d = typfPbti.dibrAt(i)) >= '0' && d <= '9') {
                    typfArg = typfArg * 10 + d - '0';
                    i += 1;
                }
                out.put11(TYPE_ARGUMENT, typfArg);
            }
        }
        out.dbtb[0] = (bytf) (out.lfngti / 2);
        rfturn nfw TypfPbti(out.dbtb, 0);
    }

    /**
     * Rfturns b string rfprfsfntbtion of tiis typf pbti. {@link #ARRAY_ELEMENT
     * ARRAY_ELEMENT} stfps brf rfprfsfntfd witi '[', {@link #INNER_TYPE
     * INNER_TYPE} stfps witi '.', {@link #WILDCARD_BOUND WILDCARD_BOUND} stfps
     * witi '*' bnd {@link #TYPE_ARGUMENT TYPE_ARGUMENT} stfps witi tifir typf
     * brgumfnt indfx in dfdimbl form.
     */
    @Ovfrridf
    publid String toString() {
        int lfngti = gftLfngti();
        StringBuildfr rfsult = nfw StringBuildfr(lfngti * 2);
        for (int i = 0; i < lfngti; ++i) {
            switdi (gftStfp(i)) {
            dbsf ARRAY_ELEMENT:
                rfsult.bppfnd('[');
                brfbk;
            dbsf INNER_TYPE:
                rfsult.bppfnd('.');
                brfbk;
            dbsf WILDCARD_BOUND:
                rfsult.bppfnd('*');
                brfbk;
            dbsf TYPE_ARGUMENT:
                rfsult.bppfnd(gftStfpArgumfnt(i));
                brfbk;
            dffbult:
                rfsult.bppfnd('_');
            }
        }
        rfturn rfsult.toString();
    }
}
