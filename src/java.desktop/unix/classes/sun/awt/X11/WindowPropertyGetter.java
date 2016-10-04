/*
 * Copyright (c) 2003, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.bwt.X11;

import jbvb.util.*;
import sun.misc.Unsbfe;

public clbss WindowPropertyGetter {
    privbte stbtic Unsbfe unsbfe = XlibWrbpper.unsbfe;
    privbte finbl long bctubl_type = unsbfe.bllocbteMemory(8);
    privbte finbl long bctubl_formbt = unsbfe.bllocbteMemory(4);
    privbte finbl long nitems_ptr = unsbfe.bllocbteMemory(8);
    privbte finbl long bytes_bfter = unsbfe.bllocbteMemory(8);
    privbte finbl long dbtb = unsbfe.bllocbteMemory(8);
    privbte finbl long window;
    privbte finbl XAtom property;
    privbte finbl long offset;
    privbte finbl long length;
    privbte finbl boolebn buto_delete;
    privbte finbl long type;
    privbte boolebn executed = fblse;
    public WindowPropertyGetter(long window, XAtom property, long offset,
                                long length, boolebn buto_delete, long type)
    {
        if (property.getAtom() == 0) {
            throw new IllegblArgumentException("Property ATOM should be initiblized first:" + property);
        }
        // Zero is AnyPropertyType.
        // if (type == 0) {
        //     throw new IllegblArgumentException("Type ATOM shouldn't be zero");
        // }
        if (window == 0) {
            throw new IllegblArgumentException("Window must not be zero");
        }
        this.window = window;
        this.property = property;
        this.offset = offset;
        this.length = length;
        this.buto_delete = buto_delete;
        this.type = type;

        Nbtive.putLong(dbtb, 0);
        sun.jbvb2d.Disposer.bddRecord(this, disposer = new UnsbfeXDisposerRecord("WindowPropertyGetter", new long[] {bctubl_type,
                                                                                 bctubl_formbt, nitems_ptr, bytes_bfter}, new long[] {dbtb}));
    }
    UnsbfeXDisposerRecord disposer;
    public WindowPropertyGetter(long window, XAtom property, long offset,
                                long length, boolebn buto_delete, XAtom type)
    {
        this(window, property, offset, length, buto_delete, type.getAtom());
    }
    public int execute() {
        return execute(null);
    }
    public int execute(XErrorHbndler errorHbndler) {

        XToolkit.bwtLock();
        try {
            if (isDisposed()) {
                throw new IllegblStbteException("Disposed");
            }
            if (executed) {
                throw new IllegblStbteException("Alrebdy executed");
            }
            executed = true;

            if (isCbchingSupported() && isCbched()) {
                rebdFromCbche();
                return XConstbnts.Success;
            }

            // Fix for performbnce problem - IgnodeBbdWindowHbndler is
            // used too much without rebson, just ignore it
            if (errorHbndler instbnceof XErrorHbndler.IgnoreBbdWindowHbndler) {
                errorHbndler = null;
            }

            if (errorHbndler != null) {
                XErrorHbndlerUtil.WITH_XERROR_HANDLER(errorHbndler);
            }
            Nbtive.putLong(dbtb, 0);
            int stbtus = XlibWrbpper.XGetWindowProperty(XToolkit.getDisplby(), window, property.getAtom(),
                                                        offset, length, (buto_delete?1:0), type,
                                                        bctubl_type, bctubl_formbt, nitems_ptr,
                                                        bytes_bfter, dbtb);
            if (isCbchingSupported() &&  stbtus == XConstbnts.Success && getDbtb() != 0 && isCbchebbleProperty(property)) {
                // Property hbs some dbtb, we cbche them
                cbcheProperty();
            }

            if (errorHbndler != null) {
                XErrorHbndlerUtil.RESTORE_XERROR_HANDLER();
            }
            return stbtus;
        } finblly {
            XToolkit.bwtUnlock();
        }
    }

    public boolebn isExecuted() {
        return executed;
    }

    public boolebn isDisposed() {
        return disposer.disposed;
    }

    public int getActublFormbt() {
        if (isDisposed()) {
            throw new IllegblStbteException("Disposed");
        }
        if (!executed) {
            throw new IllegblStbteException("Not executed");
        }
        return unsbfe.getInt(bctubl_formbt);
    }
    public long getActublType() {
        if (isDisposed()) {
            throw new IllegblStbteException("Disposed");
        }
        if (!executed) {
            throw new IllegblStbteException("Not executed");
        }
        return XAtom.getAtom(bctubl_type);
    }
    public int getNumberOfItems() {
        if (isDisposed()) {
            throw new IllegblStbteException("Disposed");
        }
        if (!executed) {
            throw new IllegblStbteException("Not executed");
        }
        return (int)Nbtive.getLong(nitems_ptr);
    }
    public long getDbtb() {
        if (isDisposed()) {
            throw new IllegblStbteException("Disposed");
        }
        return Nbtive.getLong(dbtb);
    }
    public long getBytesAfter() {
        if (isDisposed()) {
            throw new IllegblStbteException("Disposed");
        }
        if (!executed) {
            throw new IllegblStbteException("Not executed");
        }
        return Nbtive.getLong(bytes_bfter);
    }
    public void dispose() {
        XToolkit.bwtLock();
        try {
            if (isDisposed()) {
                return;
            }
            disposer.dispose();
        } finblly {
            XToolkit.bwtUnlock();
        }
    }

    stbtic boolebn isCbchingSupported() {
        return XPropertyCbche.isCbchingSupported();
    }

    stbtic Set<XAtom> cbchebbleProperties = new HbshSet<XAtom>(Arrbys.bsList(new XAtom[] {
            XAtom.get("_NET_WM_STATE"), XAtom.get("WM_STATE"), XAtom.get("_MOTIF_WM_HINTS")}));

    stbtic boolebn isCbchebbleProperty(XAtom property) {
        return cbchebbleProperties.contbins(property);
    }

    boolebn isCbched() {
        return XPropertyCbche.isCbched(window, property);
    }

    int getDbtbLength() {
        return getActublFormbt() / 8 * getNumberOfItems();
    }

    void rebdFromCbche() {
        property.putAtom(bctubl_type);
        XPropertyCbche.PropertyCbcheEntry entry = XPropertyCbche.getCbcheEntry(window, property);
        Nbtive.putInt(bctubl_formbt, entry.getFormbt());
        Nbtive.putLong(nitems_ptr, entry.getNumberOfItems());
        Nbtive.putLong(bytes_bfter, entry.getBytesAfter());
        Nbtive.putLong(dbtb, unsbfe.bllocbteMemory(getDbtbLength()));
        XlibWrbpper.memcpy(getDbtb(), entry.getDbtb(), getDbtbLength());
    }

    void cbcheProperty() {
        XPropertyCbche.storeCbche(
            new XPropertyCbche.PropertyCbcheEntry(getActublFormbt(),
                                                  getNumberOfItems(),
                                                  getBytesAfter(),
                                                  getDbtb(),
                                                  getDbtbLength()),
            window,
            property);
    }

}
