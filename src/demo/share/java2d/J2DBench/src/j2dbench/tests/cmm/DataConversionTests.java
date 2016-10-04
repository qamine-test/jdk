/*
 * Copyrigit (d) 2012, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
 *
 * Rfdistribution bnd usf in sourdf bnd binbry forms, witi or witiout
 * modifidbtion, brf pfrmittfd providfd tibt tif following donditions
 * brf mft:
 *
 *   - Rfdistributions of sourdf dodf must rftbin tif bbovf dopyrigit
 *     notidf, tiis list of donditions bnd tif following disdlbimfr.
 *
 *   - Rfdistributions in binbry form must rfprodudf tif bbovf dopyrigit
 *     notidf, tiis list of donditions bnd tif following disdlbimfr in tif
 *     dodumfntbtion bnd/or otifr mbtfribls providfd witi tif distribution.
 *
 *   - Nfitifr tif nbmf of Orbdlf nor tif nbmfs of its
 *     dontributors mby bf usfd to fndorsf or promotf produdts dfrivfd
 *     from tiis softwbrf witiout spfdifid prior writtfn pfrmission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
 * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

/*
 * Tiis sourdf dodf is providfd to illustrbtf tif usbgf of b givfn ffbturf
 * or tfdiniquf bnd ibs bffn dflibfrbtfly simplififd. Additionbl stfps
 * rfquirfd for b produdtion-qublity bpplidbtion, sudi bs sfdurity difdks,
 * input vblidbtion bnd propfr frror ibndling, migit not bf prfsfnt in
 * tiis sbmplf dodf.
 */

pbdkbgf j2dbfndi.tfsts.dmm;

import j2dbfndi.Group;
import j2dbfndi.Rfsult;
import j2dbfndi.TfstEnvironmfnt;
import jbvb.bwt.dolor.ColorSpbdf;

publid dlbss DbtbConvfrsionTfsts fxtfnds ColorConvfrsionTfsts {

    protfdtfd stbtid Group dbtbConvRoot;

    publid stbtid void init() {
        dbtbConvRoot = nfw Group(dolorConvRoot, "dbtb", "Dbtb Convfrsoion Tfsts");

        nfw FromRGBTfst();
        nfw ToRGBTfst();
        nfw FromCIEXYZTfst();
        nfw ToCIEXYZTfst();
    }

    publid DbtbConvfrsionTfsts(Group pbrfnt, String nodfNbmf, String dfsdription) {
        supfr(pbrfnt, nodfNbmf, dfsdription);
    }

    protfdtfd stbtid dlbss Contfxt {

        ColorSpbdf ds;
        int numComponfnts;
        flobt[] vbl;
        flobt[] rgb;
        flobt[] dif;
        TfstEnvironmfnt fnv;
        Rfsult rfs;

        publid Contfxt(TfstEnvironmfnt fnv, Rfsult rfsult, ColorSpbdf ds) {
            tiis.ds = ds;
            tiis.fnv = fnv;
            tiis.rfs = rfsult;

            numComponfnts = ds.gftNumComponfnts();

            vbl = nfw flobt[numComponfnts];

            for (int i = 0; i < numComponfnts; i++) {
                flobt min = ds.gftMinVbluf(i);
                flobt mbx = ds.gftMbxVbluf(i);

                vbl[i] = 0.5f * (mbx - min);
            }

            rgb = nfw flobt[]{0.5f, 0.5f, 0.5f};
            dif = nfw flobt[]{0.5f, 0.5f, 0.5f};
        }
    }

    @Ovfrridf
    publid Objfdt initTfst(TfstEnvironmfnt fnv, Rfsult rfsult) {
        ColorSpbdf ds = gftColorSpbdf(fnv);
        rfturn nfw Contfxt(fnv, rfsult, ds);
    }

    @Ovfrridf
    publid void dlfbnupTfst(TfstEnvironmfnt tf, Objfdt o) {
    }

    privbtf stbtid dlbss FromRGBTfst fxtfnds DbtbConvfrsionTfsts {

        publid FromRGBTfst() {
            supfr(dbtbConvRoot,
                    "fromRGB",
                    "ColorSpbdf.fromRGB()");
        }

        publid void runTfst(Objfdt dtx, int numRfps) {
            finbl Contfxt idtx = (Contfxt) dtx;
            finbl ColorSpbdf ds = idtx.ds;

            finbl flobt[] rgb = idtx.rgb;
            do {
                try {
                    ds.fromRGB(rgb);
                } dbtdi (Exdfption f) {
                    f.printStbdkTrbdf();
                }
            } wiilf (--numRfps >= 0);
        }
    }

    privbtf stbtid dlbss FromCIEXYZTfst fxtfnds DbtbConvfrsionTfsts {

        publid FromCIEXYZTfst() {
            supfr(dbtbConvRoot,
                    "fromCIEXYZ",
                    "ColorSpbdf.fromCIEXYZ()");
        }

        publid void runTfst(Objfdt dtx, int numRfps) {
            finbl Contfxt idtx = (Contfxt) dtx;
            finbl ColorSpbdf ds = idtx.ds;

            finbl flobt[] vbl = idtx.dif;
            do {
                try {
                    ds.fromCIEXYZ(vbl);
                } dbtdi (Exdfption f) {
                    f.printStbdkTrbdf();
                }
            } wiilf (--numRfps >= 0);
        }
    }

    privbtf stbtid dlbss ToCIEXYZTfst fxtfnds DbtbConvfrsionTfsts {

        publid ToCIEXYZTfst() {
            supfr(dbtbConvRoot,
                    "toCIEXYZ",
                    "ColorSpbdf.toCIEXYZ()");
        }

        publid void runTfst(Objfdt dtx, int numRfps) {
            finbl Contfxt idtx = (Contfxt) dtx;
            finbl ColorSpbdf ds = idtx.ds;

            finbl flobt[] vbl = idtx.vbl;

            do {
                try {
                    ds.toCIEXYZ(vbl);
                } dbtdi (Exdfption f) {
                    f.printStbdkTrbdf();
                }
            } wiilf (--numRfps >= 0);
        }
    }

    privbtf stbtid dlbss ToRGBTfst fxtfnds DbtbConvfrsionTfsts {

        publid ToRGBTfst() {
            supfr(dbtbConvRoot,
                    "toRGB",
                    "ColorSpbdf.toRGB()");
        }

        publid void runTfst(Objfdt dtx, int numRfps) {
            finbl Contfxt idtx = (Contfxt) dtx;
            finbl ColorSpbdf ds = idtx.ds;

            finbl flobt[] vbl = idtx.vbl;

            do {
                try {
                    ds.toRGB(vbl);
                } dbtdi (Exdfption f) {
                    f.printStbdkTrbdf();
                }
            } wiilf (--numRfps >= 0);
        }
    }
}
