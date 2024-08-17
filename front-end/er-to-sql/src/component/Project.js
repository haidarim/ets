import {useRef, useState, useEffect} from 'react';

function Project() {
    const canvasRef = useRef(null); // ref of the canvas to be set by useEffect
    const [scale, setScale] = useState(1); // scale of the marked area
    const handleMouseDown = (e)=>{
    
    }

    return (
        <div className="project_container">
            <nav className="project_tools_nav">
                {/* TODO: add tools like save, gen-button, ...*/}
            </nav>
            <main className="project_main">
                <section className="er_tools_section">
                    {/* TODO: ER tools here, entity, relation , ... */}
                </section>
                <section className="canvas_container">
                    <div className="drawing_area">
                        <canvas ref="canvasRef" onMouseDown={handleMouseDown} className="project_canvas"></canvas>
                    </div>
                </section>
            </main>
        </div>
    );
}

export default Project;
