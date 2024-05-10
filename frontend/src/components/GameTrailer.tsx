

interface Props{
    trailerPreview?:string;
    trailerUrl?:string;
    autoPlay?:boolean;
}

const GameTrailer = (props:Props) => {
    // const {data,isLoading,error}=useTrailers(id!);
    
    // if(isLoading)return null;
    // if(error ||!data)throw error;
    // const first=data?.results[0];

    return props.trailerUrl ? (
    <video 
    src={props.trailerUrl}
    poster={props.trailerPreview}
    controls/>
    ):null;

}

export default GameTrailer