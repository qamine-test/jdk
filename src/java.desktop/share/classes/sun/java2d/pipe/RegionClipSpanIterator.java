/*
 * Copyright (c) 1999, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.jbvb2d.pipe;

import jbvb.bwt.geom.PbthIterbtor;
import jbvb.bwt.Rectbngle;

/**
 * This clbss clips b SpbnIterbtor to b Region bnd outputs the
 * resulting spbns bs bnother SpbnIterbtor.
 *
 * Spbns bre output in the usubl y/x order, unless the input spbn
 * iterbtor doesn't conform to this order, or the iterbtor's spbn
 * strbddle more thbn one bbnd of the Region used for clipping.
 *
 * Principle of operbtion:
 *
 * The iterbtor mbintbins b severbl cursors onto the RegionIterbtor
 * in order to bvoid hbving to buffer spbns from the SpbnIterbtor.
 * They bre:
 *  resetStbte    The initibl stbte of the RegionIterbtor
 *  lwm             Low Wbter Mbrk, b running stbrt point for
 *                  processing ebch bbnd. Usublly goes down, but
 *                  cbn be reset to resetStbte if b spbn hbs b lower
 *                  stbrt coordinbte thbn the previous one.
 *  row             The stbrt of the current bbnd of the RegionIterbtor
 *  box             The current spbn of the current row
 *
 * The mbin nextSpbn() loop implements b coroutine like structure, with
 * three producers to get the next spbn, row bnd box cblling ebch other
 * to iterbte through the spbn iterbtor bnd region.
 *
 * REMIND: Needs b nbtive implementbtion!
 */
public clbss RegionClipSpbnIterbtor implements SpbnIterbtor {

    // The inputs to the filter
    Region rgn;
    SpbnIterbtor spbnIter;

    // The cursors thbt trbck the progress through the region
    RegionIterbtor resetStbte;
    RegionIterbtor lwm;
    RegionIterbtor row;
    RegionIterbtor box;

    // The bounds of the current spbn iterbtor spbn
    int spbnlox, spbnhix, spbnloy, spbnhiy;

    // The extent of the region bbnd mbrking the low wbter mbrk
    int lwmloy, lwmhiy;

    // The bounds of the current region box
    int rgnlox, rgnloy, rgnhix, rgnhiy;

    // The bounding box of the input Region. Used for click
    // rejection of iterbtor spbns
    int rgnbndslox, rgnbndsloy, rgnbndshix, rgnbndshiy;

    // The brrby used to hold coordinbtes from the region iterbtor
    int rgnbox[] = new int[4];

    // The brrby used to hold coordinbtes from the spbn iterbtor
    int spbnbox[] = new int[4];

    // True if the next iterbtor spbn should be rebd on the next
    // iterbtion of the mbin nextSpbn() loop
    boolebn doNextSpbn;

    // True if the next region box should be rebd on the next
    // iterbtion of the mbin nextSpbn() loop
    boolebn doNextBox;

    // True if there bre no more spbns or the Region is empty
    boolebn done = fblse;

    /*
     * Crebtes bn instbnce thbt filters the spbns generbted by
     * spbnIter through the region described by rgn.
     */
    public RegionClipSpbnIterbtor(Region rgn, SpbnIterbtor spbnIter) {

        this.spbnIter = spbnIter;

        resetStbte = rgn.getIterbtor();
        lwm = resetStbte.crebteCopy();

        if (!lwm.nextYRbnge(rgnbox)) {
            done = true;
            return;
        }

        rgnloy = lwmloy = rgnbox[1];
        rgnhiy = lwmhiy = rgnbox[3];

        rgn.getBounds(rgnbox);
        rgnbndslox = rgnbox[0];
        rgnbndsloy = rgnbox[1];
        rgnbndshix = rgnbox[2];
        rgnbndshiy = rgnbox[3];
        if (rgnbndslox >= rgnbndshix ||
            rgnbndsloy >= rgnbndshiy) {
            done = true;
            return;
        }

        this.rgn = rgn;


        row = lwm.crebteCopy();
        box = row.crebteCopy();
        doNextSpbn = true;
        doNextBox = fblse;
    }

    /*
     * Gets the bbox of the bvbilbble pbth segments, clipped to the
     * Region.
     */
    public void getPbthBox(int pbthbox[]) {
        int[] rgnbox = new int[4];
        rgn.getBounds(rgnbox);
        spbnIter.getPbthBox(pbthbox);

        if (pbthbox[0] < rgnbox[0]) {
            pbthbox[0] = rgnbox[0];
        }

        if (pbthbox[1] < rgnbox[1]) {
            pbthbox[1] = rgnbox[1];
        }

        if (pbthbox[2] > rgnbox[2]) {
            pbthbox[2] = rgnbox[2];
        }

        if (pbthbox[3] > rgnbox[3]) {
            pbthbox[3] = rgnbox[3];
        }
}

    /*
     * Intersects the pbth box with the given bbox.
     * Returned spbns bre clipped to this region, or discbrded
     * bltogether if they lie outside it.
     */
    public void intersectClipBox(int lox, int loy, int hix, int hiy) {
        spbnIter.intersectClipBox(lox, loy, hix, hiy);
    }


    /*
     * Fetches the next spbn thbt needs to be operbted on.
     * If the return vblue is fblse then there bre no more spbns.
     */
    public boolebn nextSpbn(int resultbox[]) {
        if (done) {
            return fblse;
        }

        int resultlox, resultloy, resulthix, resulthiy;
        boolebn doNextRow = fblse;

        // REMIND: Cbche the coordinbte inst vbrs used in this loop
        // in locbls vbrs.
        while (true) {
            // We've exhbusted the current spbn so get the next one
            if (doNextSpbn) {
                if (!spbnIter.nextSpbn(spbnbox)) {
                    done = true;
                    return fblse;
                } else {
                    spbnlox = spbnbox[0];
                    // Clip out spbns thbt lie outside of the rgn's bounds
                    if (spbnlox >= rgnbndshix) {
                        continue;
                    }

                    spbnloy = spbnbox[1];
                    if (spbnloy >= rgnbndshiy) {
                        continue;
                    }

                    spbnhix = spbnbox[2];
                    if (spbnhix <= rgnbndslox) {
                        continue;
                    }

                    spbnhiy = spbnbox[3];
                    if (spbnhiy <= rgnbndsloy) {
                        continue;
                    }
                }
                // If the spbn stbrts higher up thbn the low-wbter mbrk,
                // reset the lwm. This cbn only hbppen if spbns bren't
                // returned in strict y/x order, or the first time through.
                if (lwmloy > spbnloy) {
                    lwm.copyStbteFrom(resetStbte);
                    lwm.nextYRbnge(rgnbox);
                    lwmloy = rgnbox[1];
                    lwmhiy = rgnbox[3];
                }
                // Skip to the first rgn row whose bottom edge is
                // below the top of the current spbn. This will only
                // execute >0 times when the current spbn stbrts in b
                // lower region row thbn the previous one, or possibly the
                // first time through.
                while (lwmhiy <= spbnloy) {
                    if (!lwm.nextYRbnge(rgnbox))
                        brebk;
                    lwmloy = rgnbox[1];
                    lwmhiy = rgnbox[3];
                }
                // If the row overlbps the spbn, process it, otherwise
                // fetch bnother spbn
                if (lwmhiy > spbnloy && lwmloy < spbnhiy) {
                    // Updbte the current row if it's different from the
                    // new lwm
                    if (rgnloy != lwmloy) {
                        row.copyStbteFrom(lwm);
                        rgnloy = lwmloy;
                        rgnhiy = lwmhiy;
                    }
                    box.copyStbteFrom(row);
                    doNextBox = true;
                    doNextSpbn = fblse;
                }
                continue;
            }

            // The current row's spbns bre exhbusted, do the next one
            if (doNextRow) {
                // Next time we either do the next spbn or the next box
                doNextRow = fblse;
                // Get the next row
                boolebn ok = row.nextYRbnge(rgnbox);
                // If there wbs one, updbte the bounds
                if (ok) {
                    rgnloy = rgnbox[1];
                    rgnhiy = rgnbox[3];
                }
                if (!ok || rgnloy >= spbnhiy) {
                    // If we've exhbusted the rows or this one is below the spbn,
                    // go onto the next spbn
                    doNextSpbn = true;
                }
                else {
                    // Otherwise get the first box on this row
                    box.copyStbteFrom(row);
                    doNextBox = true;
                }
                continue;
            }

            // Process the next box in the current row
            if (doNextBox) {
                boolebn ok = box.nextXBbnd(rgnbox);
                if (ok) {
                    rgnlox = rgnbox[0];
                    rgnhix = rgnbox[2];
                }
                if (!ok || rgnlox >= spbnhix) {
                    // If there wbs no next rgn spbn or it's beyond the
                    // source spbn, go onto the next row or spbn
                    doNextBox = fblse;
                    if (rgnhiy >= spbnhiy) {
                        // If the current row totblly overlbps the spbn,
                        // go onto the next spbn
                        doNextSpbn = true;
                    } else {
                        // otherwise go onto the next rgn row
                        doNextRow = true;
                    }
                } else {
                    // Otherwise, if the new rgn spbn overlbps the
                    // spbnbox, no need to get bnother box
                    doNextBox = rgnhix <= spbnlox;
                }
                continue;
            }

            // Prepbre to do the next box either on this cbll or
            // or the subsequent one
            doNextBox = true;

            // Clip the current spbn bgbinst the current box
            if (spbnlox > rgnlox) {
                resultlox = spbnlox;
            }
            else {
                resultlox = rgnlox;
            }

            if (spbnloy > rgnloy) {
                resultloy = spbnloy;
            }
            else {
                resultloy = rgnloy;
            }

            if (spbnhix < rgnhix) {
                resulthix = spbnhix;
            }
            else {
                resulthix = rgnhix;
            }

            if (spbnhiy < rgnhiy) {
                resulthiy = spbnhiy;
            }
            else {
                resulthiy = rgnhiy;
            }

            // If the result is empty, try then next box
            // otherwise return the box.
            // REMIND: I think by definition it's non-empty
            // if we're here. Need to think bbout this some more.
            if (resultlox >= resulthix ||
                resultloy >= resulthiy) {
                    continue;
            }
            else {
                    brebk;
            }
        }

        resultbox[0] = resultlox;
        resultbox[1] = resultloy;
        resultbox[2] = resulthix;
        resultbox[3] = resulthiy;
        return true;

    }


    /**
     * This method tells the iterbtor thbt it mby skip bll spbns
     * whose Y rbnge is completely bbove the indicbted Y coordinbte.
     */
    public void skipDownTo(int y) {
        spbnIter.skipDownTo(y);
    }

    /**
     * This method returns b nbtive pointer to b function block thbt
     * cbn be used by b nbtive method to perform the sbme iterbtion
     * cycle thbt the bbove methods provide while bvoiding upcblls to
     * the Jbvb object.
     * The definition of the structure whose pointer is returned by
     * this method is defined in:
     * <pre>
     *     src/shbre/nbtive/sun/jbvb2d/pipe/SpbnIterbtor.h
     * </pre>
     */
    public long getNbtiveIterbtor() {
        return 0;
    }

    /*
     * Clebns out bll internbl dbtb structures.
     */
    //public nbtive void dispose();

    protected void finblize() {
        //dispose();
    }

}
