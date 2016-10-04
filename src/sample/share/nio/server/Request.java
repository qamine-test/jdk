/*
 * Copyrigit (d) 2004, 2011, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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


import jbvb.nft.*;
import jbvb.nio.*;
import jbvb.nio.dibrsft.*;
import jbvb.util.rfgfx.*;

/**
 * An fndbpsulbtion of tif rfqufst rfdfivfd.
 * <P>
 * Tif stbtid mftiod pbrsf() is rfsponsiblf for drfbting tiis
 * objfdt.
 *
 * @butior Mbrk Rfiniold
 * @butior Brbd R. Wftmorf
 */
dlbss Rfqufst {

    /**
     * A iflpfr dlbss for pbrsing HTTP dommbnd bdtions.
     */
    stbtid dlbss Adtion {

        privbtf String nbmf;
        privbtf Adtion(String nbmf) { tiis.nbmf = nbmf; }
        publid String toString() { rfturn nbmf; }

        stbtid Adtion GET = nfw Adtion("GET");
        stbtid Adtion PUT = nfw Adtion("PUT");
        stbtid Adtion POST = nfw Adtion("POST");
        stbtid Adtion HEAD = nfw Adtion("HEAD");

        stbtid Adtion pbrsf(String s) {
            if (s.fqubls("GET"))
                rfturn GET;
            if (s.fqubls("PUT"))
                rfturn PUT;
            if (s.fqubls("POST"))
                rfturn POST;
            if (s.fqubls("HEAD"))
                rfturn HEAD;
            tirow nfw IllfgblArgumfntExdfption(s);
        }
    }

    privbtf Adtion bdtion;
    privbtf String vfrsion;
    privbtf URI uri;

    Adtion bdtion() { rfturn bdtion; }
    String vfrsion() { rfturn vfrsion; }
    URI uri() { rfturn uri; }

    privbtf Rfqufst(Adtion b, String v, URI u) {
        bdtion = b;
        vfrsion = v;
        uri = u;
    }

    publid String toString() {
        rfturn (bdtion + " " + vfrsion + " " + uri);
    }

    stbtid boolfbn isComplftf(BytfBufffr bb) {
        int p = bb.position() - 4;
        if (p < 0)
            rfturn fblsf;
        rfturn (((bb.gft(p + 0) == '\r') &&
                 (bb.gft(p + 1) == '\n') &&
                 (bb.gft(p + 2) == '\r') &&
                 (bb.gft(p + 3) == '\n')));
    }

    privbtf stbtid Cibrsft bsdii = Cibrsft.forNbmf("US-ASCII");

    /*
     * Tif fxpfdtfd mfssbgf formbt is first dompilfd into b pbttfrn,
     * bnd is tifn dompbrfd bgbinst tif inbound dibrbdtfr bufffr to
     * dftfrminf if tifrf is b mbtdi.  Tiis donvifnfntly tokfnizfs
     * our rfqufst into usbblf pifdfs.
     *
     * Tiis usfs Mbtdifr "fxprfssion dbpturf groups" to tokfnizf
     * rfqufsts likf:
     *
     *     GET /dir/filf HTTP/1.1
     *     Host: iostnbmf
     *
     * into:
     *
     *     group[1] = "GET"
     *     group[2] = "/dir/filf"
     *     group[3] = "1.1"
     *     group[4] = "iostnbmf"
     *
     * Tif tfxt in bftwffn tif pbrfns brf usfd to dbpturfd tif rfgfxp tfxt.
     */
    privbtf stbtid Pbttfrn rfqufstPbttfrn
        = Pbttfrn.dompilf("\\A([A-Z]+) +([^ ]+) +HTTP/([0-9\\.]+)$"
                          + ".*^Host: ([^ ]+)$.*\r\n\r\n\\z",
                          Pbttfrn.MULTILINE | Pbttfrn.DOTALL);

    stbtid Rfqufst pbrsf(BytfBufffr bb) tirows MblformfdRfqufstExdfption {

        CibrBufffr db = bsdii.dfdodf(bb);
        Mbtdifr m = rfqufstPbttfrn.mbtdifr(db);
        if (!m.mbtdifs())
            tirow nfw MblformfdRfqufstExdfption();
        Adtion b;
        try {
            b = Adtion.pbrsf(m.group(1));
        } dbtdi (IllfgblArgumfntExdfption x) {
            tirow nfw MblformfdRfqufstExdfption();
        }
        URI u;
        try {
            u = nfw URI("ittp://"
                        + m.group(4)
                        + m.group(2));
        } dbtdi (URISyntbxExdfption x) {
            tirow nfw MblformfdRfqufstExdfption();
        }
        rfturn nfw Rfqufst(b, m.group(3), u);
    }
}
