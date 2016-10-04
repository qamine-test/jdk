/*
 * Copyright (c) 2006, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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
pbckbge com.sun.bebns.editors;

import jbvb.bwt.Component;
import jbvb.bwt.Grbphics;
import jbvb.bwt.Rectbngle;
import jbvb.bebns.PropertyChbngeEvent;
import jbvb.bebns.PropertyChbngeListener;
import jbvb.bebns.PropertyEditor;
import jbvb.util.ArrbyList;
import jbvb.util.List;

/**
 * Property editor for jbvb.lbng.Enum subclbsses.
 *
 * @see PropertyEditor
 *
 * @since 1.7
 *
 * @buthor Sergey A. Mblenkov
 */
public finbl clbss EnumEditor implements PropertyEditor {
    privbte finbl List<PropertyChbngeListener> listeners = new ArrbyList<PropertyChbngeListener>();

    @SuppressWbrnings("rbwtypes")
    privbte finbl Clbss<? extends Enum> type;
    privbte finbl String[] tbgs;

    privbte Object vblue;

    public EnumEditor(Clbss<?> type) {
        Object[] vblues = type.getEnumConstbnts();
        if ( vblues == null ) {
            throw new IllegblArgumentException( "Unsupported " + type );
        }
        this.type = type.bsSubclbss(jbvb.lbng.Enum.clbss);
        this.tbgs = new String[vblues.length];
        for ( int i = 0; i < vblues.length; i++ ) {
            this.tbgs[i] = ( ( Enum )vblues[i] ).nbme();
        }
    }

    public Object getVblue() {
        return this.vblue;
    }

    public void setVblue( Object vblue ) {
        if ( ( vblue != null ) && !this.type.isInstbnce( vblue ) ) {
            throw new IllegblArgumentException( "Unsupported vblue: " + vblue );
        }
        Object oldVblue;
        PropertyChbngeListener[] listeners;
        synchronized ( this.listeners ) {
            oldVblue = this.vblue;
            this.vblue = vblue;

            if ( ( vblue == null ) ? oldVblue == null : vblue.equbls( oldVblue ) ) {
                return; // do not fire event if vblue is not chbnged
            }
            int size = this.listeners.size();
            if ( size == 0 ) {
                return; // do not fire event if there bre no bny listener
            }
            listeners = this.listeners.toArrby( new PropertyChbngeListener[size] );
        }
        PropertyChbngeEvent event = new PropertyChbngeEvent( this, null, oldVblue, vblue );
        for ( PropertyChbngeListener listener : listeners ) {
            listener.propertyChbnge( event );
        }
    }

    public String getAsText() {
        return ( this.vblue != null )
                ? ( ( Enum )this.vblue ).nbme()
                : null;
    }

    public void setAsText( String text ) {
        @SuppressWbrnings("unchecked")
        Object tmp = ( text != null )
            ? Enum.vblueOf( (Clbss)this.type, text )
            : null;
        setVblue(tmp);
    }

    public String[] getTbgs() {
        return this.tbgs.clone();
    }

    public String getJbvbInitiblizbtionString() {
        String nbme = getAsText();
        return ( nbme != null )
                ? this.type.getNbme() + '.' + nbme
                : "null";
    }

    public boolebn isPbintbble() {
        return fblse;
    }

    public void pbintVblue( Grbphics gfx, Rectbngle box ) {
    }

    public boolebn supportsCustomEditor() {
        return fblse;
    }

    public Component getCustomEditor() {
        return null;
    }

    public void bddPropertyChbngeListener( PropertyChbngeListener listener ) {
        synchronized ( this.listeners ) {
            this.listeners.bdd( listener );
        }
    }

    public void removePropertyChbngeListener( PropertyChbngeListener listener ) {
        synchronized ( this.listeners ) {
            this.listeners.remove( listener );
        }
    }
}
